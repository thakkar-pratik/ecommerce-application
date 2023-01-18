package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class RegistrationException extends RuntimeException {
	
	public RegistrationException() {
		super("UNABLE_TO_REGISTER_USER");
	}

	public RegistrationException(String message) {
		super(message);
	}

}
