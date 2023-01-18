package com.te.ecommerceapplication.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer customerId;
	
	@Column(name = "first_name", nullable = false)
	private String customerFirstName;
	
	@Column(name = "last_name", nullable = false)
	private String customerLastName;
	
	@Column(name = "customer_phone", length = 10, nullable = false)
	private Long customerPhone;


//	shipping address id	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="shippngAddressId")
	private ShippingAddressDetails shippingAddressDetails;
	
//	billing address id	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="billingAddressId")
	private BillingAddressDetails billingAddressDetails;

//	userId
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="userId")
	private UserInfo userInfo;

//	CartId
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="cartId")
	private CartDetails cartDetails;

}
