package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException() {
		super("CUSTOMER_NOT_FOUND");
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}

}
