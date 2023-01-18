package com.te.ecommerceapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.ecommerceapplication.dto.CustomerInfoDto;
import com.te.ecommerceapplication.dto.ProductInfoDto;
import com.te.ecommerceapplication.dto.SalesOrderDbDetailsDto;
import com.te.ecommerceapplication.dto.UpdateProductDto;
import com.te.ecommerceapplication.response.EcomAppResponse;
import com.te.ecommerceapplication.service.AuthorityService;
import com.te.ecommerceapplication.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {



	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private CustomerService customerService;

	@Operation(summary = "It is used to fetch details of the individual customers")
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<CustomerInfoDto> fetchCustomerDetails(@PathVariable Integer customerId) {
		CustomerInfoDto customerInfoDto = customerService.getCustomer(customerId);
		if (customerInfoDto != null) {
			return new ResponseEntity<CustomerInfoDto>(customerInfoDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerInfoDto>(customerInfoDto, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerInfoDto>> fetchAllCustomers() {

		List<CustomerInfoDto> customersList = authorityService.getAllCustomers();
		if (customersList != null) {
			return new ResponseEntity<List<CustomerInfoDto>>(customersList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<CustomerInfoDto>>(customersList, HttpStatus.NO_CONTENT);
		}

	}

	@PostMapping("/products")
	public ResponseEntity<ProductInfoDto> addProduct(@RequestBody @Valid ProductInfoDto proudctInfoDto) {
		ProductInfoDto infoDto = authorityService.addProduct(proudctInfoDto);
		if (infoDto != null) {
			return new ResponseEntity<ProductInfoDto>(infoDto, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<ProductInfoDto>(infoDto, HttpStatus.NO_CONTENT);
		}
	}

//	@DeleteMapping("/deleteProduct")
//	public ResponseEntity<EcomAppResponse> deletProduct(@RequestBody deleteProductNameDto deleteProductNameDto){
//		String message = authorityService.deleteProduct(deleteProductNameDto);
//		if(message != null) {
//			return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(true, message),HttpStatus.ACCEPTED);
//		}else {
//			return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(false, "Product is not deleted"),HttpStatus.BAD_REQUEST);
//			
//		}
//	}

	@PutMapping("/products/{productId}")
	public ResponseEntity<ProductInfoDto> updateProduct(@RequestBody @Valid UpdateProductDto updateProductDto,
			@PathVariable Integer productId) {
		ProductInfoDto updatedDto = authorityService.addProduct(updateProductDto, productId);
		if (updatedDto != null) {
			return new ResponseEntity<ProductInfoDto>(updatedDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductInfoDto>(updatedDto, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/orders")
	public ResponseEntity<List<SalesOrderDbDetailsDto>> getAllSalesOrders() {
		List<SalesOrderDbDetailsDto> salesDetailsDtos = authorityService.getAllOrderDetails();
		if (salesDetailsDtos != null) {
			return new ResponseEntity<List<SalesOrderDbDetailsDto>>(salesDetailsDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<SalesOrderDbDetailsDto>>(salesDetailsDtos, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/orders/{salesOrderId}")
	public ResponseEntity<SalesOrderDbDetailsDto> getSalesOrderDetails(@PathVariable Integer salesOrderId) {
		SalesOrderDbDetailsDto salesOrderDbDetailsDto = authorityService.getOrderDetails(salesOrderId);
		if (salesOrderDbDetailsDto != null) {
			return new ResponseEntity<SalesOrderDbDetailsDto>(salesOrderDbDetailsDto, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<SalesOrderDbDetailsDto>(salesOrderDbDetailsDto, HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<EcomAppResponse> deleteCustomer(@PathVariable Integer customerId) {
		String message = authorityService.deleteParticularCustomer(customerId);
		if (message != null) {
			return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(true, message),HttpStatus.OK);
		}else {
			return new ResponseEntity<EcomAppResponse>(new EcomAppResponse(false, "Request for delete is not fulfilled"),HttpStatus.NO_CONTENT);
		}

	}
}
