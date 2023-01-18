package com.te.ecommerceapplication.service;

import java.util.List;

import com.te.ecommerceapplication.dto.CustomerInfoDto;
import com.te.ecommerceapplication.dto.ProductInfoDto;
import com.te.ecommerceapplication.dto.SalesOrderDbDetailsDto;
import com.te.ecommerceapplication.dto.UpdateProductDto;

public interface AuthorityService {

	public List<CustomerInfoDto> getAllCustomers();

	public ProductInfoDto addProduct(ProductInfoDto proudctInfoDto);

	public ProductInfoDto addProduct(UpdateProductDto updateProductDto, Integer productId);

	public List<SalesOrderDbDetailsDto> getAllOrderDetails();

	public SalesOrderDbDetailsDto getOrderDetails(Integer salesOrderId);

	public String deleteParticularCustomer(Integer customerId);

//	public String deleteProduct(deleteProductNameDto deleteProductNameDto);

}
