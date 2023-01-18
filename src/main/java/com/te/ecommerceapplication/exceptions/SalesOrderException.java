package com.te.ecommerceapplication.exceptions;

@SuppressWarnings("serial")
public class SalesOrderException extends RuntimeException {
	
	public SalesOrderException() {
		super("INVALID_ORDER_DETAILS");
	}

	public SalesOrderException(String message) {
		super(message);
	}

}
