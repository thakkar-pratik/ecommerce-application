package com.te.ecommerceapplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales_order")
public class SalesOrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer salesOrderId;
	
	
//	cart_id
	@OneToOne
	@JoinColumn(name = "cart_Id",nullable = false)
	@JsonManagedReference
	private CartDetails cartDetails;
	
//	customer_id
	@OneToOne
	@JoinColumn(name = "customer_Id", nullable = false)
	@JsonManagedReference
	private CustomerInfo customerInfo;
	
//	Shipping address_id
	@OneToOne
	@JoinColumn(name = "shippingAddress_Id", nullable = false)
	@JsonManagedReference
	private ShippingAddressDetails shippingAddressDetails;
	
//	Billing address_id
	@OneToOne
	@JoinColumn(name = "billingAddress_Id", nullable = false)
	@JsonManagedReference
	private BillingAddressDetails billingAddressDetails;

}
