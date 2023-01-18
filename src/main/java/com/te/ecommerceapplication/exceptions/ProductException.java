package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class ProductException extends RuntimeException {
	
	public ProductException() {
		super("INVALID_PRODUCT_DETAILS");
	}

	public ProductException(String message) {
		super(message);
	}

}
