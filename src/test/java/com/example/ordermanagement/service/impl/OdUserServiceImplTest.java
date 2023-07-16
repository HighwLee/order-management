package com.example.ordermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ordermanagement.domain.OdUser;
import com.example.ordermanagement.mapper.OdUserMapper;
import com.example.ordermanagement.service.OdUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class OdUserServiceImplTest {

	@Resource
	private OdUserMapper odUserMapper;


	@Resource
	private OdUserService odUserService;

	@Test
	void testUserLogin(){

		String userAccount = "test1";
		String userPassword = "test1";

		//查询用户是否存在
		QueryWrapper<OdUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("userAccount",userAccount);
		queryWrapper.eq("userPassword",userPassword);
		OdUser odUser = odUserMapper.selectOne(queryWrapper);
		System.out.println(odUser.getUserName());
		System.out.println(odUser.getUserAccount());

	}

	@Test
	void testUpdate(){
		int id = 11;
		String newPass = "asdf1111";

		int res = odUserService.userChangePass(id,newPass);
		System.out.println(res);
	}

}