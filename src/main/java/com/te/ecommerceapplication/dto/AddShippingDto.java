package com.te.ecommerceapplication.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddShippingDto {

	@NotBlank(message = "Shipping address cannot be null or empty")
	private String shippingAddress;
	
	@NotBlank(message = "City cannot be null or empty")
	private String shippingCity;
	
	@NotBlank(message = "State cannot be null or empty")
	private String shippingState;
	

//	@NotNull(message = "Enter valid ZipCode it cannot be null")
	@Pattern(regexp = "[0-9]{6}", message = "Zipcode should be of 6 digits")
	private Integer shippingZipCode;
	
	@NotBlank(message = "Country cannot be null or empty")
	private String shippingCountry;
}
