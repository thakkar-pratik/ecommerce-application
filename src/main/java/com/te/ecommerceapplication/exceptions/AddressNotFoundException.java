package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class AddressNotFoundException extends RuntimeException{

	public AddressNotFoundException() {
		super("Address Not found");
	}

	public AddressNotFoundException(String message) {
		super(message);
	}

}
