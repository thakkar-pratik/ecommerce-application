package com.te.ecommerceapplication.dto;

import org.springframework.stereotype.Component;

import com.te.ecommerceapplication.entity.BillingAddressDetails;
import com.te.ecommerceapplication.entity.CartDetails;
import com.te.ecommerceapplication.entity.ShippingAddressDetails;
import com.te.ecommerceapplication.entity.UserInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
	
	
	@NotBlank(message = "First Name cannot be left empty or null")
	private String customerFirstName;
	
	@NotNull(message = "LastName cannot be null")
	private String customerLastName;
	
	@NotNull(message = "Phone number must not be null")
	private Long customerPhone;
	
	private UserInfo userInfo;
	
	private ShippingAddressDetails shippingAddressDetails;
	
	private BillingAddressDetails billingAddressDetails;
	
	private CartDetails cartDetails;

}
