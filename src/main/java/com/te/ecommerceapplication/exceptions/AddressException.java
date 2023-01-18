package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class AddressException extends RuntimeException {
	
	public AddressException() {
		super("INVALID_ADDRESS");
	}

	public AddressException(String message) {
		super(message);
	}

}
