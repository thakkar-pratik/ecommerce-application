package com.te.ecommerceapplication.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.ecommerceapplication.dto.SearchByCategory;
import com.te.ecommerceapplication.dto.SearchByName;
import com.te.ecommerceapplication.entity.ProductInfo;
import com.te.ecommerceapplication.exceptions.ProductNotFoundException;
import com.te.ecommerceapplication.exceptions.SortingException;
import com.te.ecommerceapplication.repository.ProductInfoRepository;
import com.te.ecommerceapplication.service.ProductInfoService;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
	
	private ProductInfoRepository productInfoRepository;
	
	@Override
	public List<ProductInfo> getProductsByName(SearchByName searchByName) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(searchByName, productInfo);
		List<ProductInfo> productInfoList = productInfoRepository.findAllByProductName(productInfo.getProductName());
		if (productInfoList != null) {
			return productInfoList;
		} else {
			throw new ProductNotFoundException("PRODUCT_WITH_NAME_NOT_FOUND");
		}
	}

	@Override
	public List<ProductInfo> getProductsByCategory(SearchByCategory searchByCategory) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(searchByCategory, productInfo);
		List<ProductInfo> productByCategoryList = productInfoRepository
				.findAllByProductCategory(productInfo.getProductCategory());
		if (productByCategoryList != null) {
			return productByCategoryList;
		} else {
			throw new ProductNotFoundException("PRODUCT_WITH_NAME_NOT_FOUND");
		}
	}

	@Override
	public List<ProductInfo> sortCategoryLowToHigh(SearchByCategory searchByCategory) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(searchByCategory, productInfo);
		List<ProductInfo> productByCategory = productInfoRepository
				.findAllByProductCategory(productInfo.getProductCategory());
		List<ProductInfo> productonCategoryInAscendingOrder = productByCategory.stream()
				.sorted((prod1, prod2) -> (int) (prod1.getProductPrice() - prod2.getProductPrice()))
				.collect(Collectors.toList());
		if (productonCategoryInAscendingOrder != null) {
			return productonCategoryInAscendingOrder;
		} else {
			throw new SortingException("SOMETHING_WENT_WRONG_WHILE_SORTING");
		}
	}

	@Override
	public List<ProductInfo> sortCategoryHighToLow(SearchByCategory searchByCategory) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(searchByCategory, productInfo);
		List<ProductInfo> productByCategory = productInfoRepository
				.findAllByProductCategory(productInfo.getProductCategory());
		List<ProductInfo> productonCategoryInAscendingOrder = productByCategory.stream()
				.sorted((prod1, prod2) -> (int) (prod2.getProductPrice() - prod1.getProductPrice()))
				.collect(Collectors.toList());
		if (productonCategoryInAscendingOrder != null) {
			return productonCategoryInAscendingOrder;
		} else {
			throw new SortingException("SOMETHING_WENT_WRONG_WHILE_SORTING");
		}

	}

	@Override
	public List<ProductInfo> sortNameLowToHigh(SearchByName searchByName) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(searchByName, productInfo);
		List<ProductInfo> productByName = productInfoRepository.findAllByProductName(productInfo.getProductName());
		List<ProductInfo> productonNameInAscendingOrder = productByName.stream()
				.sorted((prod1, prod2) -> (int) (prod1.getProductPrice() - prod2.getProductPrice()))
				.collect(Collectors.toList());
		if (productonNameInAscendingOrder != null) {
			return productonNameInAscendingOrder;
		} else {
			throw new SortingException("SOMETHING_WENT_WRONG_WHILE_SORTING");
		}
	}
//	product by price sort descending in name

	@Override
	public List<ProductInfo> sortNameHighToLow(SearchByName searchByName) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(searchByName, productInfo);
		List<ProductInfo> productByName = productInfoRepository.findAllByProductName(productInfo.getProductName());
		List<ProductInfo> productonNameInDescendingOrder = productByName.stream()
				.sorted((prod1, prod2) -> (int) (prod2.getProductPrice() - prod1.getProductPrice()))
				.collect(Collectors.toList());
		if (productonNameInDescendingOrder != null) {
			return productonNameInDescendingOrder;
		} else {
			throw new SortingException("SOMETHING_WENT_WRONG_WHILE_SORTING");
		}

	}

//	Product by name Alphabetically
	@Override
	public List<ProductInfo> sortByNameAlphabetically(SearchByName searchByName) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(searchByName, productInfo);
		List<ProductInfo> productByName = productInfoRepository.findAllByProductName(productInfo.getProductName());
		List<ProductInfo> productonNameInAscendingOrder = productByName.stream()
				.sorted((prod1, prod2) -> (int) (prod1.getProductName().compareTo(prod2.getProductName())))
				.collect(Collectors.toList());
		if (productonNameInAscendingOrder != null) {
			return productonNameInAscendingOrder;
		} else {
			throw new SortingException("SOMETHING_WENT_WRONG_WHILE_SORTING");
		}
	}

	@Override
	public List<ProductInfo> sortByNameAlphabeticallyDesc(SearchByName searchByName) {
		ProductInfo productInfo = new ProductInfo();
		BeanUtils.copyProperties(searchByName, productInfo);
		List<ProductInfo> productByName = productInfoRepository.findAllByProductName(productInfo.getProductName());
		List<ProductInfo> productonNameInDescendingOrder = productByName.stream()
				.sorted((prod1, prod2) -> (int) (prod2.getProductName().compareTo(prod1.getProductName())))
				.collect(Collectors.toList());
		if (productonNameInDescendingOrder != null) {
			return productonNameInDescendingOrder;
		} else {
			throw new SortingException("SOMETHING_WENT_WRONG_WHILE_SORTING");
		}
	}

	@Override
	public List<ProductInfo> sortByCategory(SearchByCategory searchByCategory) {
		List<ProductInfo> productByCategory = productInfoRepository
				.findAllByProductCategory(searchByCategory.getProductCategory());
		List<ProductInfo> productByCategoryAscendingOrder = productByCategory.stream()
				.sorted((prod1, prod2) -> (int) (prod1.getProductName().compareTo(prod2.getProductName())))
				.collect(Collectors.toList());
		if(productByCategoryAscendingOrder != null) {
			return productByCategoryAscendingOrder; 
		}else {
			throw new SortingException("SOMETHING_WENT_WRONG_WHILE_SORTING");
		}
		
	}


}
