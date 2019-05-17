package org.avengers.boilerplate.authentication.model;

import org.avengers.boilerplate.common.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoginResponse extends BaseModel {
	private String status;
}
