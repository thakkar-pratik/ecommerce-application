package com.te.ecommerceapplication.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.ecommerceapplication.exceptions.AddressException;
import com.te.ecommerceapplication.exceptions.AddressNotFoundException;
import com.te.ecommerceapplication.exceptions.AuthenticationFailedException;
import com.te.ecommerceapplication.exceptions.CartException;
import com.te.ecommerceapplication.exceptions.CartItemException;
import com.te.ecommerceapplication.exceptions.CustomerException;
import com.te.ecommerceapplication.exceptions.CustomerInfoException;
import com.te.ecommerceapplication.exceptions.CustomerNotFoundException;
import com.te.ecommerceapplication.exceptions.ProductException;
import com.te.ecommerceapplication.exceptions.ProductNotFoundException;
import com.te.ecommerceapplication.exceptions.RegistrationException;
import com.te.ecommerceapplication.exceptions.SalesOrderException;
import com.te.ecommerceapplication.exceptions.SortingException;
import com.te.ecommerceapplication.response.EcomAppResponse;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handlingInvalidArguments(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		Map<String, String> exceptionMap = new HashMap<>();
		methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(invalidArgs -> {
			exceptionMap.put(invalidArgs.getField(), invalidArgs.getDefaultMessage());
		});
		return exceptionMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public Map<String, String> handlingInvalidArguments(Exception exception) {
		Map<String, String> exceptionMap = new HashMap<>();
		exceptionMap.put("message", exception.getMessage());
		return exceptionMap;
	}

	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<EcomAppResponse> handleAuthenticationFailedException(
			AuthenticationFailedException authenticationFailedException) {
		return new ResponseEntity<>(new EcomAppResponse(false, authenticationFailedException.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(AddressException.class)
	public ResponseEntity<EcomAppResponse> handleAddressException(AddressException addressException) {
		return new ResponseEntity<>(new EcomAppResponse(false,addressException.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<EcomAppResponse> handleAddressNotFoundException(
			AddressNotFoundException addressNotFoundException) {
		return new ResponseEntity<>(new EcomAppResponse(false, addressNotFoundException.getMessage()),
				HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(CartException.class)
	public ResponseEntity<EcomAppResponse> handleCartException(CartException cartException) {
		return new ResponseEntity<>(new EcomAppResponse(false, cartException.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CartItemException.class)
	public ResponseEntity<EcomAppResponse> handleCartItemException(CartItemException cartItemException) {
		return new ResponseEntity<>(new EcomAppResponse(false, cartItemException.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<EcomAppResponse> handleCustomerException(CustomerException custException) {
		return new ResponseEntity<>(new EcomAppResponse(false, custException.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerInfoException.class)
	public ResponseEntity<EcomAppResponse> handleCustomerInfoException(CustomerInfoException custInfoException) {
		return new ResponseEntity<>(new EcomAppResponse(false, custInfoException.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<EcomAppResponse> handleCustomerNotFoundException(
			CustomerNotFoundException customerNotFoundException) {
		return new ResponseEntity<>(new EcomAppResponse(false, customerNotFoundException.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductException.class)
	public ResponseEntity<EcomAppResponse> handleProductException(ProductException productException) {
		return new ResponseEntity<>(new EcomAppResponse(false, productException.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<EcomAppResponse> handleProductNotFoundException(
			ProductNotFoundException productNotFoundException) {
		return new ResponseEntity<>(new EcomAppResponse(false, productNotFoundException.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<EcomAppResponse> handleRegistrationException(RegistrationException registrationException) {
		return new ResponseEntity<>(new EcomAppResponse(false, registrationException.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SalesOrderException.class)
	public ResponseEntity<EcomAppResponse> handleSalesOrderException(SalesOrderException salesOrderException) {
		return new ResponseEntity<>(new EcomAppResponse(false, salesOrderException.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SortingException.class)
	public ResponseEntity<EcomAppResponse> handleSortingException(SortingException sortingException) {
		return new ResponseEntity<>(new EcomAppResponse(false, sortingException.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
}
