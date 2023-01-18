package com.te.ecommerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.ecommerceapplication.entity.ShippingAddressDetails;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddressDetails, Integer> {

}
