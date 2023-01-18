package com.te.ecommerceapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "cart_item")
public class CartItemDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer cartItemId;
	
	@Column(name = "quantity", nullable = false)
	private Integer cartItemQuantity;
	
	@Column(name = "price", nullable = false)
	private Double cartItemPrice;
	
	
	
//	cart_id	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="cartId")
	@JsonBackReference
	private CartDetails cartDetails;
	
//	product_id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="productId")
	private ProductInfo productInfo;

}
