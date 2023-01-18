package com.te.ecommerceapplication.dto;

import org.springframework.stereotype.Component;

import com.te.ecommerceapplication.entity.BillingAddressDetails;
import com.te.ecommerceapplication.entity.ShippingAddressDetails;
import com.te.ecommerceapplication.entity.UserInfo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
	
	private Integer customerId;
	
	private String customerFirstName;
	
	private String customerLastName;	
	
	private Long customerPhone;
	
	private UserInfo userInfo;
	
	private ShippingAddressDetails shippingAddressDetails;
	
	private BillingAddressDetails billingAddressDetails;

}
