package com.te.ecommerceapplication.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name ="cart")
public class CartDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer cartId;
	
	@Column(name = "total_price")
	private Double cartTotalPrice;
	
	@OneToOne(mappedBy = "cartDetails")
	@JsonBackReference
	private CustomerInfo customerInfo;
	
	@OneToMany(mappedBy = "cartDetails", cascade = CascadeType.ALL)
	private List<CartItemDetails> cartItemDetails;

}
