package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class CartItemException extends RuntimeException {
	public CartItemException() {
		super("INVALID_CART_ITEMS_DETAILS");
	}

	public CartItemException(String message) {
		super(message);
	}
}
