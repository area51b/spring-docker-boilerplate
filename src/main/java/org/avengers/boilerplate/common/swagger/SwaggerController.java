package org.avengers.boilerplate.common.swagger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@RestController
public class SwaggerController {

    @RequestMapping(method = GET, path = "/v2/api-docs", produces = APPLICATION_JSON_VALUE)
    public Resource apiDocs() {
        return new ClassPathResource("openapi.yaml");
    }

    @RequestMapping(method = GET, path = "/swagger-resources/configuration/ui", produces = APPLICATION_JSON_VALUE)
    public Object uiConfig() {
        return "{\"deepLinking\":true," +
                "\"displayOperationId\":false," +
                "\"defaultModelsExpandDepth\":1," +
                "\"defaultModelExpandDepth\":1," +
                "\"defaultModelRendering\":\"example\"," +
                "\"displayRequestDuration\":false," +
                "\"docExpansion\":\"none\"," +
                "\"filter\":false," +
                "\"operationsSorter\":\"alpha\"," +
                "\"showExtensions\":false," +
                "\"tagsSorter\":\"alpha\"," +
                "\"validatorUrl\":\"\"," +
                "\"apisSorter\":\"alpha\"," +
                "\"jsonEditor\":false," +
                "\"showRequestHeaders\":false," +
                "\"supportedSubmitMethods\":[\"get\",\"put\",\"post\",\"delete\",\"options\",\"head\",\"patch\",\"trace\"]}";
    }

    @RequestMapping(method = GET, path = "/swagger-resources/configuration/security", produces = APPLICATION_JSON_VALUE)
    public Object securityConfig() {
        return ImmutableList.of(ImmutableMap.of(
                "apiKeyVehicle", "header",
                "scopeSeparator", ",",
                "apiKeyName", "api_key"));
    }

    @RequestMapping(method = GET, path = "/swagger-resources", produces = APPLICATION_JSON_VALUE)
    public Object resources() {
        return ImmutableList.of(ImmutableMap.of(
                "name", "default",
                "url", "/v2/api-docs",
                "location", "/v2/api-docs", // should match the endpoint exposing Swagger JSON
                "swaggerVersion", "2.0"));
    }
}