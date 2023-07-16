package com.example.ordermanagement.domain.request;

import lombok.Data;

@Data
public class RegisterUser {
	/**
	 *
	 */
	private String userAccount;

	/**
	 *
	 */
	private String userName;

	/**
	 *
	 */
	private String userPassword;

//	/**
//	 *
//	 */
//	private String avatarUrl;

	/**
	 *
	 */
	private String userCheckPassword;

	/**
	 *
	 */
	private String userPhone;

	/**
	 *
	 */
	private String UserGender;
}
