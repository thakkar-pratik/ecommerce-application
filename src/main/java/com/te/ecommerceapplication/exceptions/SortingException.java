package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class SortingException extends RuntimeException {
	
	public SortingException() {
		super("PLEASE_ENTER_VALID_PRODUCTS_FOR_SORTING");
	}

	public SortingException(String message) {
		super(message);
	}
}
