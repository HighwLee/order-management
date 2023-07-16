package com.example.ordermanagement.domain.request;


import lombok.Data;

@Data
public class DeleteOrder {

	private int id;

	private String belongWho;
}
