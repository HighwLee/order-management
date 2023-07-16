package com.example.ordermanagement.domain.request;


import lombok.Data;

@Data
public class EditOrder {

	private Integer id;

	/**
	 *
	 */
	private String equipmentName;

	/**
	 *
	 */
	private String supplyName;

	/**
	 *
	 */
	private Integer counts;


}
