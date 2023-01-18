package com.te.ecommerceapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.ecommerceapplication.dto.BillingAddressDetailsDto;
import com.te.ecommerceapplication.dto.CustomerInfoDto;
import com.te.ecommerceapplication.dto.ShippingAddressDetailsDto;
import com.te.ecommerceapplication.response.EcomAppResponse;
import com.te.ecommerceapplication.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<EcomAppResponse> deleteCustomer(@PathVariable Integer customerId) {
		String message = customerService.deleteCustomer(customerId);
		if (message != null) {
			return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(true, message), HttpStatus.OK);
		} else {
			return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(false, "Enter valid id to delete customer"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerInfoDto> fetchCustomerDetails(@PathVariable Integer customerId) {
		CustomerInfoDto customerInfoDto = customerService.getCustomer(customerId);
		if (customerInfoDto != null) {
			return new ResponseEntity<CustomerInfoDto>(customerInfoDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerInfoDto>(customerInfoDto, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateBilling")
	public ResponseEntity<EcomAppResponse> updateBillingAddress(
			@RequestBody BillingAddressDetailsDto updateBillingAddressDto) {
		BillingAddressDetailsDto updatedBillingAddress = customerService.updateBillingAddress(updateBillingAddressDto);
		if (updatedBillingAddress != null) {
			return new ResponseEntity<EcomAppResponse>(
					new EcomAppResponse(true, "Billing address is successfully updated"), HttpStatus.OK);
		} else {

			return new ResponseEntity<EcomAppResponse>(
					new EcomAppResponse(false, "Unavailable to update the Billing address"), HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("{customerId}/billing")
	public ResponseEntity<EcomAppResponse> deleteBillingAddress(@PathVariable Integer customerId) {
		String deleteBillingAddressMsg = customerService.deleteBillingAddress(customerId);
		if (deleteBillingAddressMsg != null) {
			return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(true, deleteBillingAddressMsg),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(false, deleteBillingAddressMsg),
					HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("{customerId}/shipping")
	public ResponseEntity<EcomAppResponse> deleteShippingAddress(@PathVariable Integer customerId) {

		String deleteShippingAddressMsg = customerService.deleteShippingAddress(customerId);
		if (deleteShippingAddressMsg != null) {
			return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(true, deleteShippingAddressMsg),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(false, deleteShippingAddressMsg),
					HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/{customerId}/billing")
	public ResponseEntity<BillingAddressDetailsDto> addBillingAddress(@PathVariable Integer customerId,
			@RequestBody BillingAddressDetailsDto billingAddressDetailsDto) {
		BillingAddressDetailsDto dto = customerService.addBillingAddress(customerId, billingAddressDetailsDto);
		if (dto != null) {
			return new ResponseEntity<BillingAddressDetailsDto>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<BillingAddressDetailsDto>(dto, HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping("/{customerId}/shipping")
	public ResponseEntity<ShippingAddressDetailsDto> addShippingAddress(@PathVariable Integer customerId,
			@RequestBody ShippingAddressDetailsDto shippingAddressDetailsDto) {
		ShippingAddressDetailsDto dto = customerService.addShippingAddress(customerId, shippingAddressDetailsDto);
		if (dto != null) {
			return new ResponseEntity<ShippingAddressDetailsDto>(dto, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<ShippingAddressDetailsDto>(dto, HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping("/updateShipping")
	public ResponseEntity<EcomAppResponse> updateShippingAddress(
			@RequestBody ShippingAddressDetailsDto updateShippingAddressDto) {
		ShippingAddressDetailsDto updatedShippingAddress = customerService
				.updateShippingAddress(updateShippingAddressDto);
		if (updatedShippingAddress != null) {
			return new ResponseEntity<EcomAppResponse>(
					new EcomAppResponse(true, "Shipping address is successfully updated"), HttpStatus.OK);
		} else {

			return new ResponseEntity<EcomAppResponse>(
					new EcomAppResponse(false, "Unavailable to update the shipping address"), HttpStatus.NO_CONTENT);
		}
	}

//	Get Both the Addresses
	@GetMapping("/{customerId}/getBothAddress")
	public ResponseEntity<List<Object>> getBothAddress(@PathVariable Integer customerId) {
		List<Object> bothAddress = customerService.bothAddress(customerId);
		if (bothAddress != null) {
			return new ResponseEntity<List<Object>>(bothAddress, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Object>>(bothAddress, HttpStatus.NO_CONTENT);
		}
	}



		
}
