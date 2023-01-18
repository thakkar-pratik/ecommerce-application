package com.te.ecommerceapplication.service;

import com.te.ecommerceapplication.dto.RegisterDto;
import com.te.ecommerceapplication.dto.SignInDto;
import com.te.ecommerceapplication.response.SignInResponse;

public interface UserService {

	public void addCustomer(RegisterDto registerDto);

	public SignInResponse signIn(SignInDto signInDto);
	
	



}
