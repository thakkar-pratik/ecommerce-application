package com.te.ecommerceapplication.service;

import com.te.ecommerceapplication.dto.CartItemDetailsDto;

public interface CartService {
	
	public CartItemDetailsDto addToCart(Integer customerId, Integer productId,Integer productnos);

	public CartItemDetailsDto getCartDetails(Integer cartItemId);

	public Double getCartPrice(Integer cartId);

}
