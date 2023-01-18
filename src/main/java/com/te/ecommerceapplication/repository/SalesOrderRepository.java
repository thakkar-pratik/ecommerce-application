package com.te.ecommerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.ecommerceapplication.entity.SalesOrderDetails;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrderDetails, Integer> {

}
