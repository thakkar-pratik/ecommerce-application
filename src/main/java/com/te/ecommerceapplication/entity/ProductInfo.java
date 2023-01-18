package com.te.ecommerceapplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "product")
public class ProductInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer productId;

	@Column(name = "category", nullable = false)
	private String productCategory;

	@Column(name = "description", nullable = false)
	private String productDescription;

	@Column(name = "manufacturer", nullable = false)
	private String productManufacturer;

	@Column(name = "name", nullable = false)
	private String productName;

	@Column(name = "price", nullable = false)
	private Double productPrice;

	@Column(name = "unit", nullable = false)
	private Integer productUnit;

}
