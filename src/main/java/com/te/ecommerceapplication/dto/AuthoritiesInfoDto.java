package com.te.ecommerceapplication.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthoritiesInfoDto {
	
	private Integer authorityId;
	
	@Email(message = "Invalid Email id")
	private String authorityEmailId;
	
	private String authorities;
}
