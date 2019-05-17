package org.avengers.boilerplate.authentication;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import javax.validation.Valid;

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

@RestController
@RequestMapping(value = "/boilerplate/v1/")
public class AuthenticationController extends BaseController {

	@Autowired
	private AuthenticationService authenticationService;

	private final String COMMON_API_KEY = "683QNZJCNPUTKGMM5XGHT492Q7SO5R1MQ4IUB3BG";

	@PostMapping(value = "/login", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public LoginResponse login(@RequestHeader("api_key") String apiKey,
			@RequestBody @Valid LoginRequest request, 
			BindingResult binding) throws Exception {

		if(!COMMON_API_KEY.equals(apiKey)) {
			throw new Exception("Not authorized to access the service.");
		}
		
		return authenticationService.login(request);
	}

}
