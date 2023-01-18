package com.te.ecommerceapplication.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EcomAppResponse {
	
	private final boolean success;
	
	private final String message;
	
	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}
	
	
	
	
	

}
