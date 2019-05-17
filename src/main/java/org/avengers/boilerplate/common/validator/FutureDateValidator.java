package org.avengers.boilerplate.common.validator;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FutureDateValidator implements
ConstraintValidator<FutureDateConstraint, Date> {

	@Override
	public void initialize(FutureDateConstraint contactNumber) {
	}

	@Override
	public boolean isValid(Date dateField,
			ConstraintValidatorContext cxt) {
		return (new Date()).before(dateField);
	}

}
