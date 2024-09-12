package org.avengers.boilerplate.authentication;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.avengers.boilerplate.authentication.model.LoginRequest;
import org.avengers.boilerplate.authentication.model.LoginResponse;
import org.avengers.boilerplate.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.milvus.client.MilvusClient;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.ShowCollectionsResponse;
import io.milvus.orm.iterator.QueryIterator;
import io.milvus.param.ConnectParam;
import io.milvus.param.R;
import io.milvus.param.collection.ShowCollectionsParam;
import io.milvus.param.dml.QueryIteratorParam;
import io.milvus.response.QueryResultsWrapper;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/boilerplate/v1/")
public class AuthenticationController extends BaseController {

	@Autowired
	private AuthenticationService authenticationService;

	private final String COMMON_API_KEY = "683QNZJCNPUTKGMM5XGHT492Q7SO5R1MQ4IUB3BG";

	@PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public LoginResponse login(@RequestHeader("api_key") String apiKey,
			@RequestBody @Valid LoginRequest request, 
			BindingResult binding) throws Exception {

		if(!COMMON_API_KEY.equals(apiKey)) {
			throw new Exception("Not authorized to access the service.");
		}

		try {						
			ConnectParam connectParam = ConnectParam.newBuilder()
				.withHost("localhost")
				.withPort(19530)
				.build();
			MilvusClient milvusClient = new MilvusServiceClient(connectParam);

			ShowCollectionsParam param = ShowCollectionsParam.newBuilder().build();
			R<ShowCollectionsResponse> response1 = milvusClient.withTimeout(2, TimeUnit.SECONDS).showCollections(param);

			System.out.println(response1);

			// Define the collection name
			String COLLECTION_NAME = "multi_turn_rag";

			// Define the query expression
			String queryExpr = "pk > 0";

			// Define the query limit
			//int queryLimit = 10000; // Set to a large number to retrieve all records

			R<QueryIterator> response = milvusClient.queryIterator(QueryIteratorParam.newBuilder()
					.withCollectionName(COLLECTION_NAME)
					.withExpr(queryExpr)
					.addOutField("pk")
					.addOutField("source")
					.addOutField("text")
					.addOutField("vector")
					.withBatchSize(100L)
					.build());
			if (response.getStatus() != R.Status.Success.getCode()) {
				System.out.println(response.getMessage());
			}

			QueryIterator queryIterator = response.getData();
			int count = 0;
			// Write the results to a CSV file
			String csvFile = "results.csv";
			try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
				while (true) {
					List<QueryResultsWrapper.RowRecord> batchResults = queryIterator.next();
					if (batchResults.size() == 0) {
						System.out.println("query iteration finished, close");
						queryIterator.close();
						System.out.println("query result size: " + count);
						break;
					}
					else {
						//System.out.println("query result size: " + batchResults.size());
						count += batchResults.size();
					}

					// Write the header
					StringBuilder header = new StringBuilder();
					header.append("pk").append(",");
					header.append("source").append(",");
					header.append("text").append(",");
					header.append("vector");
					
					// header.append("text");

					writer.println(header.toString().replaceAll(",$", ""));

					for (QueryResultsWrapper.RowRecord res : batchResults) {
						//System.out.println(res);
						StringBuilder row = new StringBuilder();
						row.append(res.get("pk")).append(",");
						row.append(res.get("source")).append(",");
						row.append(res.get("text").toString().replaceAll(",", "-")).append(",");
						row.append(res.get("vector"));

						// row.append(res.get("text").toString().replaceAll(",", "-"));

						//write to csv file
						writer.println(row.toString());
					}
				}
				writer.flush();

			} catch (IOException e) {
				System.err.println("Error writing to CSV file: " + e.getMessage());
			}

		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return authenticationService.login(request);
	}

}
