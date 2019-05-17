package org.avengers.boilerplate.authentication;

import javax.validation.Valid;

import org.avengers.boilerplate.authentication.model.LoginRequest;
import org.avengers.boilerplate.authentication.model.LoginResponse;
import org.avengers.boilerplate.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService extends BaseService {

	@Autowired
	private AuthenticationRepository authenticationRepository;

	public LoginResponse login(@Valid LoginRequest request) {
		//String status = authenticationRepository.login(request.getUsername(), request.getPassword());
		String status = "success";
		
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setStatus(status);
		return loginResponse;
	}

}
