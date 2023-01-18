package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class CustomerException extends RuntimeException {
	
	public CustomerException() {
		super("Invalid Customer Details");
	}

	public CustomerException(String message) {
		super(message);
	}

}
