package com.te.ecommerceapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.ecommerceapplication.dto.CartItemDetailsDto;
import com.te.ecommerceapplication.service.CartService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	
//	 Add To Cart	
	@PostMapping("/{customerId}/addToCart/{productnos}/{productId}")
	public ResponseEntity<CartItemDetailsDto> addToCart(@PathVariable Integer customerId,
			@PathVariable Integer productnos, @PathVariable Integer productId, @RequestParam("token") String token) {
		CartItemDetailsDto cartItemDetailsDto = cartService.addToCart(customerId, productId, productnos);
		if (cartItemDetailsDto != null) {
			return new ResponseEntity<CartItemDetailsDto>(cartItemDetailsDto, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<CartItemDetailsDto>(cartItemDetailsDto, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/cartDetails/{cartItemId}")
	public ResponseEntity<CartItemDetailsDto> getCartDetails(@PathVariable Integer cartItemId) {
		CartItemDetailsDto cartItemDetailsDto = cartService.getCartDetails(cartItemId);
		if (cartItemDetailsDto != null) {
			return new ResponseEntity<CartItemDetailsDto>(cartItemDetailsDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<CartItemDetailsDto>(cartItemDetailsDto, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/price/{cartId}")
	public ResponseEntity<Double> cartPrice(@PathVariable Integer cartId) {
		Double cartTotal = cartService.getCartPrice(cartId);
		if (cartTotal != null) {
			return new ResponseEntity<Double>(cartTotal, HttpStatus.OK);
		} else {
			return new ResponseEntity<Double>(cartTotal, HttpStatus.NO_CONTENT);
		}
	}

}
