package com.te.ecommerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.ecommerceapplication.entity.CartDetails;

@Repository
public interface CartRepository extends JpaRepository<CartDetails, Integer> {

}
