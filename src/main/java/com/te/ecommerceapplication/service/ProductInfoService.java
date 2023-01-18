package com.te.ecommerceapplication.service;

import java.util.List;

import com.te.ecommerceapplication.dto.SearchByCategory;
import com.te.ecommerceapplication.dto.SearchByName;
import com.te.ecommerceapplication.entity.ProductInfo;

public interface ProductInfoService {
	
	
	
	
	public List<ProductInfo> getProductsByName(SearchByName searchByName);

	public List<ProductInfo> getProductsByCategory(SearchByCategory searchByCategory);

	public List<ProductInfo> sortCategoryLowToHigh(SearchByCategory searchByCategory);

	public List<ProductInfo> sortCategoryHighToLow(SearchByCategory searchByCategory);

	public List<ProductInfo> sortNameLowToHigh(SearchByName searchByName);

	public List<ProductInfo> sortNameHighToLow(SearchByName searchByName);

	public List<ProductInfo> sortByNameAlphabetically(SearchByName searchByName);

	public List<ProductInfo> sortByNameAlphabeticallyDesc(SearchByName searchByName);

	public List<ProductInfo> sortByCategory(SearchByCategory searchByCategory);


}
