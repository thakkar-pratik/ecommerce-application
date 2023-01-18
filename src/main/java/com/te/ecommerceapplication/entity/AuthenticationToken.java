package com.te.ecommerceapplication.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "tokens")
public class AuthenticationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer tokenId;

	private String token;

	@Column(name = "created_date")
	private Date createdDate;

	@OneToOne(targetEntity = UserInfo.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private UserInfo userInfo;

	public AuthenticationToken(UserInfo userInfo) {
		this.tokenId = userInfo.getUserId();
		this.userInfo = userInfo;
		this.createdDate = new Date();
		this.token = UUID.randomUUID().toString();
	}

}
