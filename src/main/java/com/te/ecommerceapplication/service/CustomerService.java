package com.te.ecommerceapplication.service;

import java.util.List;

import com.te.ecommerceapplication.dto.BillingAddressDetailsDto;
import com.te.ecommerceapplication.dto.CustomerInfoDto;
import com.te.ecommerceapplication.dto.ShippingAddressDetailsDto;

public interface CustomerService {
	
	public String deleteCustomer(Integer customerId);

	public CustomerInfoDto getCustomer(Integer customerId);

	public CustomerInfoDto updateCustomer(Integer customerId, CustomerInfoDto customerInfoDto);

	public BillingAddressDetailsDto updateBillingAddress(BillingAddressDetailsDto updateBillingAddressDto);

	public String deleteBillingAddress(Integer customerId);

	public String deleteShippingAddress(Integer customerId);

	public BillingAddressDetailsDto addBillingAddress(Integer customerId,
			BillingAddressDetailsDto billingAddressDetailsDto);

	public ShippingAddressDetailsDto addShippingAddress(Integer customerId,
			ShippingAddressDetailsDto shippingAddressDetailsDto);

	public ShippingAddressDetailsDto updateShippingAddress(ShippingAddressDetailsDto updateShippingAddressDto);

	public List<Object> bothAddress(Integer customerId);

}
