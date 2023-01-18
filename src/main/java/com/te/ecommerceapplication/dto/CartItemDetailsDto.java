package com.te.ecommerceapplication.dto;

import org.springframework.stereotype.Component;


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
public class CartItemDetailsDto {
	
	private Integer cartItemId;
	
	@NotNull(message = "Item Quantity cannot be null")
	private Integer cartItemQuantity;
	

	private Double cartItemPrice;
	
	
	private Integer cartId;
	
	private Integer productId;

	
	
}
