package com.te.ecommerceapplication.serviceimpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.ecommerceapplication.entity.AuthenticationToken;
import com.te.ecommerceapplication.entity.UserInfo;
import com.te.ecommerceapplication.exceptions.AuthenticationFailedException;
import com.te.ecommerceapplication.repository.TokenRepository;
import com.te.ecommerceapplication.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepository.save(authenticationToken);
	}

	@Override
	public AuthenticationToken getToken(UserInfo user) {
		return tokenRepository.findByUserInfo(user);
	}

	@Override
	public void authenticate(String token) throws AuthenticationFailedException { 
		if (Objects.isNull(token)) {
			throw new AuthenticationFailedException("TOKEN_NOT_PRESENT");
		}
		if (Objects.isNull(getUser(token))) {
			throw new AuthenticationFailedException("TOKEN_NOT_VALID");
		}
	}

	private UserInfo getUser(String token) {
		final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
		if (Objects.isNull(authenticationToken)) {
			return null;
		}
		return authenticationToken.getUserInfo();
	}

}
