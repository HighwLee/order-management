package com.example.ordermanagement.service;

import com.example.ordermanagement.domain.OdList;
import com.example.ordermanagement.domain.OdUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordermanagement.domain.request.RegisterUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface OdUserService extends IService<OdUser> {

	long userRegister(RegisterUser registerUser);

	OdUser userLogin(String userAccount, String userPassword, HttpServletRequest request);

	int userChangePass(long UserId,String newPass);

	List<OdUser> selectAllUser();
}
