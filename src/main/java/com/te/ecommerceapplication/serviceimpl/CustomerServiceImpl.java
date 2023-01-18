package com.te.ecommerceapplication.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.ecommerceapplication.dto.BillingAddressDetailsDto;
import com.te.ecommerceapplication.dto.CustomerInfoDto;
import com.te.ecommerceapplication.dto.ProductInfoDto;
import com.te.ecommerceapplication.dto.ShippingAddressDetailsDto;
import com.te.ecommerceapplication.entity.BillingAddressDetails;
import com.te.ecommerceapplication.entity.CartDetails;
import com.te.ecommerceapplication.entity.CustomerInfo;
import com.te.ecommerceapplication.entity.ProductInfo;
import com.te.ecommerceapplication.entity.ShippingAddressDetails;
import com.te.ecommerceapplication.exceptions.AddressException;
import com.te.ecommerceapplication.exceptions.AddressNotFoundException;
import com.te.ecommerceapplication.exceptions.CustomerException;
import com.te.ecommerceapplication.exceptions.CustomerInfoException;
import com.te.ecommerceapplication.exceptions.CustomerNotFoundException;
import com.te.ecommerceapplication.repository.BillingAddressRepository;
import com.te.ecommerceapplication.repository.CustomerInfoRepository;
import com.te.ecommerceapplication.repository.ShippingAddressRepository;
import com.te.ecommerceapplication.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private BillingAddressRepository billingAddressRepository;

	@Autowired
	private CustomerInfoRepository customerInfoRepository;

	@Autowired
	private ShippingAddressRepository shippingAddressRepository;

	public CustomerInfoDto customerToCustomerDto(CustomerInfo customerInfo) {
		CustomerInfoDto customerInfoDto = new CustomerInfoDto();
		customerInfoDto.setCustomerId(customerInfo.getCustomerId());
		customerInfoDto.setCustomerFirstName(customerInfo.getCustomerFirstName());
		customerInfoDto.setCustomerLastName(customerInfo.getCustomerLastName());
		customerInfoDto.setCustomerPhone(customerInfo.getCustomerPhone());
		try {
		customerInfoDto.setUserInfo(customerInfo.getUserInfo());
		}catch(NullPointerException e) {
			customerInfoDto.setUserInfo(null);
		}
		try {
		customerInfoDto.setCartId(customerInfo.getCartDetails().getCartId());
		}catch(NullPointerException e) {
			customerInfoDto.setCartId(null);
	
		}
		try {			
			customerInfoDto.setBillingAddressDetails(customerInfo.getBillingAddressDetails());
		}catch(NullPointerException e) {
			customerInfo.setBillingAddressDetails(null);
		}
		try {
		customerInfoDto.setShippingAddressDetails(customerInfo.getShippingAddressDetails());
		}catch(NullPointerException e) {
			customerInfoDto.setShippingAddressDetails(null);
		}
		return customerInfoDto;
	}

	@Override
	public BillingAddressDetailsDto updateBillingAddress(BillingAddressDetailsDto updateBillingAddressDto) {
		BillingAddressDetails billingAddress = new BillingAddressDetails();
		BeanUtils.copyProperties(updateBillingAddressDto, billingAddress);
		BillingAddressDetails billingDbAddress = billingAddressRepository.findById(billingAddress.getBillingId())
				.orElseThrow(AddressNotFoundException::new);
		if (billingDbAddress != null) {
			if (billingDbAddress.getBillingId() == updateBillingAddressDto.getBillingId()) {
				billingAddressRepository.save(billingAddress);
				return updateBillingAddressDto;
			} else {
				throw new AddressException("ADDRESS_NOT_SAVED");
			}
		} else {
			throw new AddressException("SOMETHING_WENT_WRONG");
		}
	}

	@Override
	public CustomerInfoDto updateCustomer(Integer customerId, CustomerInfoDto customerInfoDto) {
		CustomerInfo customerInfo = customerInfoRepository.findById(customerId).get();
		if (customerInfo != null) {
			customerInfo.setCustomerFirstName(customerInfoDto.getCustomerFirstName());
			customerInfo.setCustomerLastName(customerInfoDto.getCustomerLastName());
			customerInfo.setCustomerPhone(customerInfoDto.getCustomerPhone());
			customerInfoRepository.save(customerInfo);
			return customerToCustomerDto(customerInfo);
		} else {
			throw new CustomerInfoException("CUSTOMER_NOT_FOUND");
		}
	}

	@Override
	public String deleteBillingAddress(Integer customerId) {

		CustomerInfo customerInfo = customerInfoRepository.findById(customerId).get();
		Integer deleteBilling = customerInfo.getBillingAddressDetails().getBillingId();
		if (deleteBilling != null) {
			customerInfo.setBillingAddressDetails(null);
			billingAddressRepository.deleteById(deleteBilling);
			return "Billing Address is deleted";
		} else {
			throw new AddressException("Couldn't delete the billing address");
		}
	}

	@Override
	public String deleteShippingAddress(Integer customerId) {
		CustomerInfo customerInfo = customerInfoRepository.findById(customerId).get();
		Integer deleteShipping = customerInfo.getShippingAddressDetails().getShippingId();
		if (deleteShipping != null) {
			customerInfo.setShippingAddressDetails(null);
			shippingAddressRepository.deleteById(deleteShipping);
			return "Shipping Address is deleted";
		} else {
			throw new AddressException("Couldn't delete the Shipping address");
		}
	}

	@Override
	public BillingAddressDetailsDto addBillingAddress(Integer customerId,
			BillingAddressDetailsDto billingAddressDetailsDto) {
		CustomerInfo customerInfo = customerInfoRepository.findById(customerId).get();
		if (customerInfo != null) {
			BillingAddressDetails billingAddressDetails = billingDtoToBilling(billingAddressDetailsDto);
			customerInfo.setBillingAddressDetails(billingAddressDetails);
			BillingAddressDetails addressDetails = billingAddressRepository
					.save(customerInfo.getBillingAddressDetails());
			customerInfoRepository.save(customerInfo);
			BeanUtils.copyProperties(addressDetails, billingAddressDetailsDto);
			if (addressDetails != null) {
				return billingAddressDetailsDto;
			} else {
				throw new AddressException("Failed To add Billing Address. Try after sometime.");
			}
		} else {
			throw new CustomerException("Customer Not Found");
		}
	}

	@Override
	public ShippingAddressDetailsDto addShippingAddress(Integer customerId,
			ShippingAddressDetailsDto shippingAddressDetailsDto) {
		CustomerInfo customerInfo = customerInfoRepository.findById(customerId).get();
		if (customerInfo != null) {
			ShippingAddressDetails shippingAddressDetails = shippingDtoToShipping(shippingAddressDetailsDto);
			customerInfo.setShippingAddressDetails(shippingAddressDetails);
			ShippingAddressDetails addressDetails = shippingAddressRepository
					.save(customerInfo.getShippingAddressDetails());
			customerInfoRepository.save(customerInfo);
			BeanUtils.copyProperties(addressDetails, shippingAddressDetailsDto);
			if (addressDetails != null) {
				return shippingAddressDetailsDto;
			} else {
				throw new AddressException("Failed To add Shipping Address. Try after sometime.");
			}
		} else {
			throw new CustomerException("Customer Not Found");
		}
	}

	public BillingAddressDetailsDto billingToBillingDto(BillingAddressDetails billingAddressDetails) {
		BillingAddressDetailsDto billingAddressDetailsDto = new BillingAddressDetailsDto();
		BeanUtils.copyProperties(billingAddressDetails, billingAddressDetailsDto);
		return billingAddressDetailsDto;
	}

	public BillingAddressDetails billingDtoToBilling(BillingAddressDetailsDto billingAddressDetailsDto) {
		BillingAddressDetails billingAddressDetails = new BillingAddressDetails();
		BeanUtils.copyProperties(billingAddressDetailsDto, billingAddressDetails);
		return billingAddressDetails;
	}

	public ShippingAddressDetailsDto shippingToShippingDto(ShippingAddressDetails shippingAddressDetails) {
		ShippingAddressDetailsDto shippingAddressDetailsDto = new ShippingAddressDetailsDto();
		BeanUtils.copyProperties(shippingAddressDetails, shippingAddressDetailsDto);
		return shippingAddressDetailsDto;
	}

	public ShippingAddressDetails shippingDtoToShipping(ShippingAddressDetailsDto shippingAddressDetailsDto) {
		ShippingAddressDetails shippingAddressDetails = new ShippingAddressDetails();
		BeanUtils.copyProperties(shippingAddressDetailsDto, shippingAddressDetails);
		return shippingAddressDetails;
	}

	@Override
	public ShippingAddressDetailsDto updateShippingAddress(ShippingAddressDetailsDto updateShippingAddressDto) {
		ShippingAddressDetails shippingAddress = new ShippingAddressDetails();
		BeanUtils.copyProperties(updateShippingAddressDto, shippingAddress);
		ShippingAddressDetails shippingDbAddress = shippingAddressRepository.findById(shippingAddress.getShippingId())
				.orElseThrow(AddressNotFoundException::new);
		if (shippingDbAddress != null) {
			if (shippingDbAddress.getShippingId() == updateShippingAddressDto.getShippingId()) {
				shippingAddressRepository.save(shippingAddress);
				return updateShippingAddressDto;
			} else {
				throw new AddressException("ADDRESS_NOT_SAVED");
			}
		} else {
			throw new AddressException("SOMETHING_WENT_WRONG");
		}
	}

	@Override
	public List<Object> bothAddress(Integer customerId) {
		List<Object> addressList = new ArrayList<>();
		CustomerInfo customerInfo = customerInfoRepository.findById(customerId).get();
		if (customerInfo != null) {
			BillingAddressDetails billingAddressDetails = customerInfo.getBillingAddressDetails();
			if (billingAddressDetails != null) {
				addressList.add(billingAddressDetails);
			} else {
				throw new AddressException("BILLING_ADDRESS_NOT_FOUND");
			}
			ShippingAddressDetails shippingAddressDetails = customerInfo.getShippingAddressDetails();
			if (shippingAddressDetails != null) {
				addressList.add(shippingAddressDetails);
			} else {
				throw new AddressException("SHIPPING_ADDRESS_NOT_FOUND");
			}
			return addressList;
		} else {
			throw new CustomerException("CUSTOMER_NOT_FOUND");
		}
	}

	
//	Product to productDto
	public ProductInfoDto productToProductDto(ProductInfo productInfo) {
		ProductInfoDto productInfoDto = new ProductInfoDto();
		BeanUtils.copyProperties(productInfo, productInfoDto);
		return productInfoDto;
	}

//	product dto to product
	public ProductInfo productDtoToProduct(ProductInfoDto productInfoDto) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(productInfoDto, productInfo);
		return productInfo;
	}
	
	
	@Override
	public String deleteCustomer(Integer customerId) {
		CustomerInfo customerInfo = customerInfoRepository.findById(customerId).get();
		CartDetails cartDetails = customerInfo.getCartDetails();
		if (customerInfo != null && cartDetails == null) {
			customerInfoRepository.deleteById(customerId);
			return "SUCCESSFULLY_DELETED";
		} else {
			throw new CustomerNotFoundException("CUSTOMER_NOT_FOUND OR CART IS PRESENT");
		}
	}

	@Override
	public CustomerInfoDto getCustomer(Integer customerId) {
		CustomerInfo customerInfo = customerInfoRepository.findById(customerId).get();
		if (customerInfo != null) {
			CustomerInfoDto customerInfoDto = customerToCustomerDto(customerInfo);
			return customerInfoDto;
		} else {
			return null;
		}
	}


	

	
	

	
}
