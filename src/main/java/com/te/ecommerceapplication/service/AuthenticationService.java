package com.te.ecommerceapplication.service;

import com.te.ecommerceapplication.entity.AuthenticationToken;
import com.te.ecommerceapplication.entity.UserInfo;
import com.te.ecommerceapplication.exceptions.AuthenticationFailedException;

public interface AuthenticationService {

	public void saveConfirmationToken(AuthenticationToken authenticationToken);

	public AuthenticationToken getToken(UserInfo user);
	
	public void authenticate(String token) throws AuthenticationFailedException;
	

}
