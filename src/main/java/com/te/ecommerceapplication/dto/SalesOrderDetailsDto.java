package com.te.ecommerceapplication.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDetailsDto {
	
	private Integer salesOrderId;
	
	private Integer cartId;
	
	private Integer customerId;
	
	private Integer shippingId;
	
	private Integer billingId;

	

}
