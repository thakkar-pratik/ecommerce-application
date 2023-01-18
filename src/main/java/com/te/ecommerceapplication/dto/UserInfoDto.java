package com.te.ecommerceapplication.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {

	private Integer userId;
	
	@Email(message = "Enter valid email id")
	private String userEmailId;
	
	@NotBlank(message = "Enter valid password")
	private String userPassword;
	
	
	private boolean userEnabled;
	
	
	
	

}
