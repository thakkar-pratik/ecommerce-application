package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class CartException extends RuntimeException {
	public CartException() {
		super("INVALID_CART_DETAILS");
	}

	public CartException(String message) {
		super(message);
	}
}
