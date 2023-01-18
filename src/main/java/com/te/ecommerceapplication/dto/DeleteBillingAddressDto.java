package com.te.ecommerceapplication.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBillingAddressDto {
	
	@NotNull(message = "billing Id cannot be null")
	private Integer billingId;

}
