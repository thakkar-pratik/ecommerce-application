package com.te.ecommerceapplication.serviceimpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.ecommerceapplication.dto.RegisterDto;
import com.te.ecommerceapplication.dto.SignInDto;
import com.te.ecommerceapplication.entity.AuthenticationToken;
import com.te.ecommerceapplication.entity.CustomerInfo;
import com.te.ecommerceapplication.entity.UserInfo;
import com.te.ecommerceapplication.exceptions.AuthenticationFailedException;
import com.te.ecommerceapplication.exceptions.RegistrationException;
import com.te.ecommerceapplication.repository.BillingAddressRepository;
import com.te.ecommerceapplication.repository.CustomerInfoRepository;
import com.te.ecommerceapplication.repository.ShippingAddressRepository;
import com.te.ecommerceapplication.repository.UserInfoRepository;
import com.te.ecommerceapplication.response.SignInResponse;
import com.te.ecommerceapplication.service.AuthenticationService;
import com.te.ecommerceapplication.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CustomerInfoRepository customerInfoRepository;

	@Autowired
	private ShippingAddressRepository shippingAddressRepository;

	@Autowired
	private BillingAddressRepository billingAddressRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private AuthenticationService authenticationService;

	@Transactional
	@Override
	public void addCustomer(RegisterDto registerDto) {
		if (Objects.nonNull(userInfoRepository.findByuserEmailId(registerDto.getUserInfo().getUserEmailId()))) {
			throw new RegistrationException("USER_WITH_SAME_EMAIL_EXISTS");
		} else {
			CustomerInfo customerInfo = new CustomerInfo();
			UserInfo userInfo = registerDto.getUserInfo();
			try {
				userInfo.setUserPassword(hashPassword(registerDto.getUserInfo().getUserPassword()));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			registerDto.setUserInfo(userInfo);
			BeanUtils.copyProperties(registerDto, customerInfo);
			shippingAddressRepository.save(customerInfo.getShippingAddressDetails());
			billingAddressRepository.save(customerInfo.getBillingAddressDetails());
			userInfoRepository.save(customerInfo.getUserInfo());
			customerInfoRepository.save(customerInfo);
//	   		creating the token
			final AuthenticationToken authenticationToken = new AuthenticationToken(userInfo);
			authenticationService.saveConfirmationToken(authenticationToken);
		}
	}

	private String hashPassword(String textPass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(textPass.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	@Override
	public SignInResponse signIn(SignInDto signInDto) {
//		find user by email
		UserInfo user = userInfoRepository.findByuserEmailId(signInDto.getEmail());

		if (Objects.isNull(user)) {
			throw new AuthenticationFailedException("INVALID_LOGIN_CREDENTIALS");
		}

//		hash the password
//		compare the password in db
		try {
			if (!user.getUserPassword().equalsIgnoreCase(hashPassword(signInDto.getPassword()))) {
				throw new AuthenticationFailedException("INVALID_LOGIN_CREDENTIALS");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
//		if password matches
		AuthenticationToken token = authenticationService.getToken(user);

//		retrieve token
		if(Objects.isNull(token)) {
			throw new AuthenticationFailedException("TOKEN_NOT_FOUND");
		}

//		return response
		return new SignInResponse("success", token.getToken());
	}

}
