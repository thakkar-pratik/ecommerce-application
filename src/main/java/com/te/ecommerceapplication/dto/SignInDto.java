package com.te.ecommerceapplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
	
	@Email(message = "Enter valid email id")
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	
	

}
