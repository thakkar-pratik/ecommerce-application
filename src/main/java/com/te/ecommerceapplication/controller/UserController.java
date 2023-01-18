package com.te.ecommerceapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.ecommerceapplication.dto.RegisterDto;
import com.te.ecommerceapplication.dto.SignInDto;
import com.te.ecommerceapplication.response.EcomAppResponse;
import com.te.ecommerceapplication.response.SignInResponse;
import com.te.ecommerceapplication.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<EcomAppResponse> registerCustomer(@RequestBody @Valid RegisterDto registerDto) {
		userService.addCustomer(registerDto);
		return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(true, "Customer registered successfully"),
				HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<SignInResponse> signIn(@RequestBody SignInDto signInDto) {
		SignInResponse signInResponse = userService.signIn(signInDto);
		return new ResponseEntity<SignInResponse>(signInResponse, HttpStatus.OK);
	}

}
