package com.te.ecommerceapplication.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillingAddressDetailsDto {
	
	private Integer billingId;
	
	@NotBlank(message = "Billing address cannot be left empty or null")
	private String billingAddress;
	
	@NotBlank(message = "City cannot be left empty or null")
	private String billingCity;
	
	@NotBlank(message = "State cannot be left empty or null")
	private String billingState;
	
//	@NotNull(message = "Enter valid ZipCode it cannot be null")
	@Pattern(regexp = "[0-9]{6}", message = "Zipcode should be of 6 digits")
	private String billingZipCode;
	
	@NotBlank(message = "Country field cannot be empty or null")
	private String billingCountry;
	
	

}
