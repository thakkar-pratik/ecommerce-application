package com.te.ecommerceapplication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class deleteProductNameDto {

	@NotBlank(message = "Product name is either empty or null")
	private String productName;

}
