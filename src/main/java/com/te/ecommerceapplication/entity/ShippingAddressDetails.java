package com.te.ecommerceapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shipping_address")
public class ShippingAddressDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer shippingId;
	
	@Column(nullable = false, name = "address")
	private String shippingAddress;
	
	@Column(nullable = false, name = "city")
	private String shippingCity;
	
	@Column(nullable = false, name = "state")
	private String shippingState;
	
	@Column(nullable = false, name = "zipcode", length = 6)
	private Integer shippingZipCode;
	
	@Column(nullable = false, name = "country")
	private String shippingCountry;

	@OneToOne(mappedBy = "shippingAddressDetails")
	@JsonBackReference
	private CustomerInfo customerInfo;
}
