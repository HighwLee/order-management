package com.example.ordermanagement.domain.request;


import lombok.Data;

@Data
public class ChangePassUser {

	private long id;

	private String newPass;
}
