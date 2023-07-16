package com.example.ordermanagement.service;

import com.example.ordermanagement.domain.request.RegisterUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OdUserServiceTest {

	@Autowired
	private OdUserService odUserService;


	@Test
	void testSave(){
		RegisterUser odUser = new RegisterUser();
		odUser.setUserAccount("test");
		odUser.setUserName("test");
		odUser.setUserPassword("test");
		odUser.setUserPhone("test");
		odUser.setUserGender("man");
		long res = odUserService.userRegister(odUser);
//		assertEquals(odUser.getId(),res);
	}

}