package com.te.ecommerceapplication.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDto {
	
	private Integer productId;
	
	private String productCategory;
	
	private String productDescription;
	
	private String productManufacturer;
	
	@NotBlank(message = "product name must not be null")
	private String productName;
	
	@NotNull(message = "product name must not be null")
	private Double productPrice;
	
	private Integer productUnit;

}
