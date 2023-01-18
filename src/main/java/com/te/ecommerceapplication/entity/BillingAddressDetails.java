package com.te.ecommerceapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "billing_address")
public class BillingAddressDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer billingId;

	@Column(nullable = false, name = "address")
	private String billingAddress;

	@Column(nullable = false, name = "city")
	private String billingCity;
	
	@Column(nullable = false, name = "state")
	private String billingState;

	@Column(nullable = false, length = 6, name = "zipcode")
	private String billingZipCode;

	@Column(nullable = false, name = "country")
	private String billingCountry;
	
	@OneToOne(mappedBy = "billingAddressDetails")
	@JsonBackReference
	private CustomerInfo customerInfo;

}
