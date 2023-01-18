package com.te.ecommerceapplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.ecommerceapplication.entity.ProductInfo;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {
	List<ProductInfo> findAllByProductName(String productName);

	List<ProductInfo> findAllByProductCategory(String productCateGory);

	Optional<ProductInfo> findByProductName(String productName);

}
