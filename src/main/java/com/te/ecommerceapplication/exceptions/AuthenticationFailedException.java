package com.te.ecommerceapplication.exceptions;


@SuppressWarnings("serial")
public class AuthenticationFailedException extends RuntimeException {
	
	public AuthenticationFailedException(String message) {
		super(message);
	}

}
