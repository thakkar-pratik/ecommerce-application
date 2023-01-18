package com.te.ecommerceapplication.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.ecommerceapplication.dto.CustomerInfoDto;
import com.te.ecommerceapplication.dto.ProductInfoDto;
import com.te.ecommerceapplication.dto.SalesOrderDbDetailsDto;
import com.te.ecommerceapplication.dto.UpdateProductDto;
import com.te.ecommerceapplication.entity.CartDetails;
import com.te.ecommerceapplication.entity.CustomerInfo;
import com.te.ecommerceapplication.entity.ProductInfo;
import com.te.ecommerceapplication.entity.SalesOrderDetails;
import com.te.ecommerceapplication.exceptions.CustomerException;
import com.te.ecommerceapplication.exceptions.CustomerNotFoundException;
import com.te.ecommerceapplication.exceptions.ProductException;
import com.te.ecommerceapplication.exceptions.SalesOrderException;
import com.te.ecommerceapplication.repository.CustomerInfoRepository;
import com.te.ecommerceapplication.repository.ProductInfoRepository;
import com.te.ecommerceapplication.repository.SalesOrderRepository;
import com.te.ecommerceapplication.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private CustomerInfoRepository customerInfoRepository;

	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Autowired
	private SalesOrderRepository salesOrderRepository;

	public CustomerInfoDto customerToCustomerDto(CustomerInfo customerInfo) {
		CustomerInfoDto customerInfoDto = new CustomerInfoDto();
		customerInfoDto.setCustomerId(customerInfo.getCustomerId());
		customerInfoDto.setCustomerFirstName(customerInfo.getCustomerFirstName());
		customerInfoDto.setCustomerLastName(customerInfo.getCustomerLastName());
		customerInfoDto.setCustomerPhone(customerInfo.getCustomerPhone());
		try {
			customerInfoDto.setUserInfo(customerInfo.getUserInfo());
		} catch (NullPointerException e) {
			customerInfoDto.setUserInfo(null);
		}
		try {
			customerInfoDto.setCartId(customerInfo.getCartDetails().getCartId());
		} catch (NullPointerException e) {
			customerInfoDto.setCartId(0);
		}
		try {
			customerInfoDto.setBillingAddressDetails(customerInfo.getBillingAddressDetails());
		} catch (NullPointerException e) {
			customerInfoDto.setBillingAddressDetails(null);
		}
		try {
			customerInfoDto.setShippingAddressDetails(customerInfo.getShippingAddressDetails());
		} catch (NullPointerException e) {
			customerInfoDto.setShippingAddressDetails(null);
		}
		return customerInfoDto;
	}

	@Override
	public List<CustomerInfoDto> getAllCustomers() {
		List<CustomerInfo> customersList = customerInfoRepository.findAll();
		List<CustomerInfoDto> customerDtoList = new ArrayList<CustomerInfoDto>();
		if (customersList != null) {
			customersList.forEach(customerInfo -> {
				CustomerInfoDto customerInfoDto = customerToCustomerDto(customerInfo);
				customerDtoList.add(customerInfoDto);
			});
			return customerDtoList;
		} else {
			throw new CustomerException("CUSTOMER_NOT_FOUND");
		}
	}

	@Override
	public ProductInfoDto addProduct(ProductInfoDto proudctInfoDto) {
		ProductInfo productInfo = productDtoToProduct(proudctInfoDto);
		ProductInfo savedProduct = productInfoRepository.save(productInfo);
		ProductInfoDto dto = productToProductDto(savedProduct);
		if (savedProduct != null) {
			return dto;
		} else {
			throw new ProductException("PRODUCT_NOT_FOUND");
		}
	}

	public ProductInfoDto productToProductDto(ProductInfo productInfo) {
		ProductInfoDto productInfoDto = new ProductInfoDto();
		BeanUtils.copyProperties(productInfo, productInfoDto);
		return productInfoDto;
	}

	public ProductInfo productDtoToProduct(ProductInfoDto productInfoDto) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(productInfoDto, productInfo);
		return productInfo;
	}

	@Override
	public ProductInfoDto addProduct(UpdateProductDto updateProductDto, Integer productId) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(updateProductDto, productInfo);
		ProductInfo productDb = productInfoRepository.findById(productId).get();
		if (productDb != null) {
			productDb.setProductUnit(productInfo.getProductUnit());
			ProductInfo savedProductInfo = productInfoRepository.save(productDb);
			ProductInfoDto productInfoDto = new ProductInfoDto();
			BeanUtils.copyProperties(savedProductInfo, productInfoDto);
			return productInfoDto;
		} else {
			throw new ProductException("PRODUCT_NOT_PRESENT");
		}
	}

	@Override
	public List<SalesOrderDbDetailsDto> getAllOrderDetails() {
		List<SalesOrderDetails> salesOrdersDb = salesOrderRepository.findAll();
		if (salesOrdersDb != null) {
			List<SalesOrderDbDetailsDto> salesOrderDbDetailsDto = new ArrayList<SalesOrderDbDetailsDto>();
			SalesOrderDbDetailsDto dbOrderDetailsDto = new SalesOrderDbDetailsDto();
			salesOrdersDb.forEach(order -> {
				BeanUtils.copyProperties(order, dbOrderDetailsDto);
				salesOrderDbDetailsDto.add(dbOrderDetailsDto);
			});
			return salesOrderDbDetailsDto;
		} else {
			throw new SalesOrderException("ORDERS_NOT_FOUND");
		}
	}

	@Override
	public SalesOrderDbDetailsDto getOrderDetails(Integer salesOrderId) {
		SalesOrderDetails salesOrderDetailsDb = salesOrderRepository.findById(salesOrderId).get();
		if (salesOrderDetailsDb != null) {
			SalesOrderDbDetailsDto salesOrderDbDetailsDto = new SalesOrderDbDetailsDto();
			BeanUtils.copyProperties(salesOrderDetailsDb, salesOrderDbDetailsDto);
			return salesOrderDbDetailsDto;
		} else {
			throw new SalesOrderException("SALES_ORDER_NOT_FOUND");
		}
	}

	@Override
	public String deleteParticularCustomer(Integer customerId) {
		CustomerInfo customerInfo = customerInfoRepository.findById(customerId).get();
		CartDetails cartDetails = customerInfo.getCartDetails();
		if (customerInfo != null && cartDetails == null) {
			customerInfoRepository.deleteById(customerId);
			return "SUCCESSFULLY_DELETED";
		} else {
			throw new CustomerNotFoundException("CUSTOMER_NOT_FOUND");
		}
	}
}
