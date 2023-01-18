package com.te.ecommerceapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.te.ecommerceapplication.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "id")
	private Integer userId;

	@Column(nullable = false, name = "email", unique = true)
	private String userEmailId;

	@Column(nullable = false, name = "password")
	private String userPassword;

	@Column(nullable = false, name = "enabled")
	private boolean userEnabled;

	@OneToOne(mappedBy = "userInfo")
	@JsonBackReference
	private CustomerInfo customerInfo;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

}
