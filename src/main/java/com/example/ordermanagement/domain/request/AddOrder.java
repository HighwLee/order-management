package com.example.ordermanagement.domain.request;


import lombok.Data;

@Data
public class AddOrder {


	private String belongWho;

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
