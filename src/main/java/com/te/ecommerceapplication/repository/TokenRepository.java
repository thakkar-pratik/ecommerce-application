package com.te.ecommerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.ecommerceapplication.entity.AuthenticationToken;
import com.te.ecommerceapplication.entity.UserInfo;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
	
	AuthenticationToken findByUserInfo(UserInfo userInfo);

	
	AuthenticationToken findByToken(String token);
	

}
