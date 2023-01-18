package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class CustomerInfoException extends RuntimeException {
	
	public CustomerInfoException() {
		super("Customer not found");
	}

	public CustomerInfoException(String message) {
		super(message);
	}

}
