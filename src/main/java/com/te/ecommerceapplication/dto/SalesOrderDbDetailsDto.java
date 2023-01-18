package com.te.ecommerceapplication.dto;

import com.te.ecommerceapplication.entity.BillingAddressDetails;
import com.te.ecommerceapplication.entity.CartDetails;
import com.te.ecommerceapplication.entity.CustomerInfo;
import com.te.ecommerceapplication.entity.ShippingAddressDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDbDetailsDto {
	
	private Integer salesOrderId;
	
	private CartDetails cartDetails;
	
	private CustomerInfo customerInfo;
	
	private ShippingAddressDetails shippingAddressDetails;
	
	private BillingAddressDetails billingAddressDetails;
	
	

}
