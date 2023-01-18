package com.te.ecommerceapplication.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.ecommerceapplication.dto.CartItemDetailsDto;
import com.te.ecommerceapplication.entity.CartDetails;
import com.te.ecommerceapplication.entity.CartItemDetails;
import com.te.ecommerceapplication.entity.CustomerInfo;
import com.te.ecommerceapplication.entity.ProductInfo;
import com.te.ecommerceapplication.entity.SalesOrderDetails;
import com.te.ecommerceapplication.exceptions.CartException;
import com.te.ecommerceapplication.exceptions.CartItemException;
import com.te.ecommerceapplication.exceptions.ProductException;
import com.te.ecommerceapplication.exceptions.ProductNotFoundException;
import com.te.ecommerceapplication.repository.CartItemDetailsRepository;
import com.te.ecommerceapplication.repository.CartRepository;
import com.te.ecommerceapplication.repository.CustomerInfoRepository;
import com.te.ecommerceapplication.repository.ProductInfoRepository;
import com.te.ecommerceapplication.repository.SalesOrderRepository;
import com.te.ecommerceapplication.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartItemDetailsRepository cartItemDetailsRepository;

	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Autowired
	private CustomerInfoRepository customerInfoRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private SalesOrderRepository salesOrderRepository;

	@Override
	public CartItemDetailsDto addToCart(Integer customerId, Integer productId, Integer productnos) {
		CartItemDetails cartItemDetails = new CartItemDetails();
		CartDetails cart = new CartDetails();

//		Converting productDto to product & Set Quantity of cart item
		ProductInfo productInfo = productInfoRepository.findById(productId).get();
		Integer productUnitnos = productInfo.getProductUnit();
		if (productInfo != null && productnos <= productUnitnos) {
			cartItemDetails.setProductInfo(productInfo);
			cartItemDetails.setCartItemQuantity(productnos);
			productInfo.setProductUnit(productInfo.getProductUnit() - productnos);
			productInfoRepository.save(cartItemDetails.getProductInfo());
		} else {
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND");
		}

//		updating price of CartItem 
		cartItemDetails.setCartItemPrice(productInfo.getProductPrice());

//		Calculating total price
		Double price = productInfo.getProductPrice();
		Double total = price * productnos;

//		Saving total price to cart
		cart.setCartTotalPrice(total);
		CartDetails savedCart = cartRepository.save(cart);

		if (savedCart != null) {

//		Saving cart to the customer
			CustomerInfo customerInfo = customerInfoRepository.findById(customerId).orElse(null);

//		Save to salesOrder
			SalesOrderDetails salesOrderDetails = new SalesOrderDetails();
			salesOrderDetails.setBillingAddressDetails(customerInfo.getBillingAddressDetails());
			salesOrderDetails.setCartDetails(cart);
			salesOrderDetails.setShippingAddressDetails(customerInfo.getShippingAddressDetails());

			if (customerInfo != null) {
				customerInfo.setCartDetails(cart);
				salesOrderDetails.setCustomerInfo(customerInfo);
				customerInfoRepository.save(customerInfo);
				salesOrderRepository.save(salesOrderDetails);
			}

		} else {
			throw new CartException("NO_PRODUCTS_FOUND");
		}

//		cartItemDetails.setProductInfo(productInfo);
		cartItemDetails.setCartDetails(savedCart);
//		save cart item
		CartItemDetails savedItem = cartItemDetailsRepository.save(cartItemDetails);
		if (savedItem != null) {
			CartItemDetailsDto cartItemDetailsDto = new CartItemDetailsDto();
			BeanUtils.copyProperties(savedItem, cartItemDetailsDto);
			cartItemDetailsDto.setCartId(savedItem.getCartDetails().getCartId());
			cartItemDetailsDto.setProductId(savedItem.getProductInfo().getProductId());
			return cartItemDetailsDto;
		} else {

			throw new CartItemException("NOTHING_IN_CART");
		}
	}

	public Double cartItemPrice(CartItemDetails cartItemDetails) {
		int productId = cartItemDetails.getProductInfo().getProductId();
		ProductInfo productInfo = productInfoRepository.findById(productId).get();
		if (cartItemDetails.getCartItemQuantity() < productInfo.getProductUnit()) {
			Double cartItemPrice = cartItemDetails.getCartItemQuantity() * productInfo.getProductPrice();
			cartItemDetails.setCartItemPrice(cartItemPrice);
			return cartItemPrice;
		} else {
			throw new ProductException("PRODUCT_OUT_OF_STOCK");
		}

	}

	@Override
	public CartItemDetailsDto getCartDetails(Integer cartItemId) {
		CartItemDetails cartItemDetails = cartItemDetailsRepository.findById(cartItemId).get();
		if (cartItemDetails != null) {
			CartItemDetailsDto cartItemDetailsDto = cartItemToCartItemDto(cartItemDetails);
			return cartItemDetailsDto;
		} else {
			throw new CartItemException("CART_ITEM_NOT_FOUND");
		}
	}

	@Override
	public Double getCartPrice(Integer cartId) {
		CartDetails cartDetails = cartRepository.findById(cartId).get();
		if (cartDetails != null) {
			return cartDetails.getCartTotalPrice();
		} else {
			throw new CartException("CART_DETAILS_NOT FOUND");
		}
	}

	public CartItemDetailsDto cartItemToCartItemDto(CartItemDetails cartItemDetails) {
		CartItemDetailsDto cartItemDetailsDto = new CartItemDetailsDto();
		cartItemDetailsDto.setCartItemId(cartItemDetails.getCartItemId());
		cartItemDetailsDto.setCartItemPrice(cartItemDetails.getCartItemPrice());
		cartItemDetailsDto.setCartId(cartItemDetails.getCartDetails().getCartId());
		cartItemDetailsDto.setProductId(cartItemDetails.getProductInfo().getProductId());
		cartItemDetailsDto.setCartItemQuantity(cartItemDetails.getCartItemQuantity());
		return cartItemDetailsDto;
	}

	public CartItemDetails cartItemDtoToCartItem(CartItemDetailsDto cartItemDetailsDto) {
		CartItemDetails cartItemDetails = new CartItemDetails();
		ProductInfo productInfo = productInfoRepository.findById(cartItemDetailsDto.getProductId()).get();
		cartItemDetails.setCartItemPrice(cartItemDetailsDto.getCartItemPrice());
		cartItemDetails.setCartDetails(cartRepository.findById(cartItemDetailsDto.getCartId()).get());
		cartItemDetails.setProductInfo(productInfo);
		cartItemDetails.setCartItemQuantity(cartItemDetailsDto.getCartItemQuantity());
		return cartItemDetails;
	}

}
