package com.te.ecommerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.ecommerceapplication.entity.CartItemDetails;

@Repository
public interface CartItemDetailsRepository extends JpaRepository<CartItemDetails, Integer> {

}
