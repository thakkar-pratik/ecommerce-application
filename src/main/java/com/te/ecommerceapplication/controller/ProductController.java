package com.te.ecommerceapplication.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.ecommerceapplication.dto.SearchByCategory;
import com.te.ecommerceapplication.dto.SearchByName;
import com.te.ecommerceapplication.entity.ProductInfo;
import com.te.ecommerceapplication.service.ProductInfoService;

@RestController
@RequestMapping("/customers/products")
public class ProductController {
	
	private ProductInfoService productInfoService;
	
	
	@GetMapping("/allProductsByName")
	public ResponseEntity<List<ProductInfo>> getAllProductsByName(@RequestBody SearchByName searchByName) {
		List<ProductInfo> productInfoList = productInfoService.getProductsByName(searchByName);
		if (productInfoList != null) {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/allProductsByCategory")
	public ResponseEntity<List<ProductInfo>> getAllProductsByCategory(@RequestBody SearchByCategory searchByCategory) {
		List<ProductInfo> productInfoList = productInfoService.getProductsByCategory(searchByCategory);
		if (productInfoList != null) {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.NO_CONTENT);
		}
	}

//	Sorting product on price in ascending order on price 
	@GetMapping("/categoryPriceLowToHigh")
	public ResponseEntity<List<ProductInfo>> cartegoryPriceLowToHigh(@RequestBody SearchByCategory searchByCategory) {
		List<ProductInfo> productInfoList = productInfoService.sortCategoryLowToHigh(searchByCategory);
		if (productInfoList != null) {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.NO_CONTENT);
		}
	}
	
//	Sorting Product category in descending order on price
	@GetMapping("/categoryPriceHighToLow")
	public ResponseEntity<List<ProductInfo>> categoryPriceHighToLow(@RequestBody SearchByCategory searchByCategory){
		List<ProductInfo> productInfoList = productInfoService.sortCategoryHighToLow(searchByCategory);
		if(productInfoList != null){
			return new ResponseEntity<List<ProductInfo>>(productInfoList,HttpStatus.OK);
		}else {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.NO_CONTENT);
		}
	}
	
	
//	Sorting with category Alphabetically
	@GetMapping("/categoryAlphabetically")
	public ResponseEntity<List<ProductInfo>> categoryAlphabetically(@RequestBody SearchByCategory searchByCategory){
		List<ProductInfo> productInfoList = productInfoService.sortByCategory(searchByCategory);
		if(productInfoList != null){
			return new ResponseEntity<List<ProductInfo>>(productInfoList,HttpStatus.OK);
		}else {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.NO_CONTENT);
		}
	}
	
	
//	Sorting Product name in Ascending order on price
	@GetMapping("/namePriceLowToHigh")
	public ResponseEntity<List<ProductInfo>> namePriceLowToHigh(@RequestBody SearchByName searchByName) {
		List<ProductInfo> productInfoList = productInfoService.sortNameLowToHigh(searchByName);
		if (productInfoList != null) {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.NO_CONTENT);
		}
	}
	
//	Sorting Product name in Descending order on price
	@GetMapping("/namePriceHighToLow")
	public ResponseEntity<List<ProductInfo>> namePriceHighToLow(@RequestBody SearchByName searchByName){
		List<ProductInfo> productInfoList = productInfoService.sortNameHighToLow(searchByName);
		if (productInfoList != null) {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.NO_CONTENT);
		}	
	}
	
//	Sort product by name alphabetically
	@GetMapping("/nameAlphabetically")
	public ResponseEntity<List<ProductInfo>> nameAlphabetically(@RequestBody SearchByName searchByName){
		List<ProductInfo> productInfoList  = productInfoService.sortByNameAlphabetically(searchByName);
		if (productInfoList != null) {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.NO_CONTENT);
		}
	}
		
//		Sort product by name reverse alphabets i.e form z to a
		@GetMapping("/nameAlphabeticallyDesc")
		public ResponseEntity<List<ProductInfo>> nameAlphabeticallyDesc(@RequestBody SearchByName searchByName){
			List<ProductInfo> productInfoList  = productInfoService.sortByNameAlphabeticallyDesc(searchByName);
			if (productInfoList != null) {
				return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<ProductInfo>>(productInfoList, HttpStatus.NO_CONTENT);
			}
	}


}
