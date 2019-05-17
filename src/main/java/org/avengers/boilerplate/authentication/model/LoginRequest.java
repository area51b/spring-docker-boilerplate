package org.avengers.boilerplate.authentication.model;

import javax.validation.constraints.NotBlank;

import org.avengers.boilerplate.common.base.BaseModel;
import org.avengers.boilerplate.common.validator.SQLInjectionSafe;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoginRequest extends BaseModel {

	@NotBlank(message="\"username\" cannot be empty.")
	@SQLInjectionSafe
	private String username;
	
	@NotBlank(message="\"password\" cannot be empty.")
	@SQLInjectionSafe
	private String password;
}
