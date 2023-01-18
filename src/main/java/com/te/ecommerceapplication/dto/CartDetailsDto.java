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
public class CartDetailsDto {
	
	private Integer CartId;
	
	@NotNull(message = "Cart total value cannot be null")
	private Double cartTotalPrice;

}
