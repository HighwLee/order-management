package com.example.ordermanagement.controller;


import com.example.ordermanagement.domain.OdList;
import com.example.ordermanagement.domain.OdUser;
import com.example.ordermanagement.domain.request.*;
import com.example.ordermanagement.service.OdListService;
import com.example.ordermanagement.service.OdUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private OdUserService odUserService;

	@Resource
	private OdListService odListService;

	@PostMapping("/register")
	@ResponseBody
	public Long UserRegister(@RequestBody RegisterUser registerUser){
		if (registerUser == null){
			return null;
		}
		return odUserService.userRegister(registerUser);
	}

	@PostMapping("/login")
	@ResponseBody
	public OdUser userLogin(@RequestBody LoginUser loginUser, HttpServletRequest request){
		if (loginUser == null){
			return null;
		}
		String userAccount = loginUser.getUserAccount();
		String userPassword = loginUser.getUserPassword();
		if (StringUtils.isAnyBlank(userAccount,userPassword)){
			return null;
		}
		return odUserService.userLogin(userAccount,userPassword,request);
	}

	@PostMapping("/changePass")
	@ResponseBody
	public int changePass(@RequestBody ChangePassUser changePassUser){
		if (StringUtils.isAnyBlank(changePassUser.getNewPass())){
			return -1;
		}
		return odUserService.userChangePass(changePassUser.getId(),changePassUser.getNewPass());
	}

	@PostMapping("/searchAll")
	@ResponseBody
	public List<OdList> searchAll(@RequestBody SearchAllOrder searchAllOrder){
		if (searchAllOrder == null){
			return null;
		}
		return odListService.selectAll(searchAllOrder.getBelongWho());
	}

	@PostMapping("/searchAllUser")
	@ResponseBody
	public List<OdUser> searchAllUser(){
		return odUserService.selectAllUser();
	}

	@PostMapping("/searchByEntity")
	@ResponseBody
	public List<OdList> searchByEntity(@RequestBody SearchOrder searchOrder){
		if (StringUtils.isAnyBlank(searchOrder.getSearchColumn())){
			return null;
		}
		return odListService.selectByEntity(searchOrder.getSearchColumn(),searchOrder.getBelongWho());
	}

	@PostMapping("/addOrder")
	@ResponseBody
	public int addOrder(@RequestBody AddOrder addOrder){
		if (addOrder == null){
			return -1;
		}
		return odListService.insertOne(addOrder);
	}

	@DeleteMapping("/deleteAll")
	@ResponseBody
	public void deleteAll(@RequestBody DeleteOrder deleteOrder){
		if (deleteOrder == null){
			return ;
		}
		odListService.deleteAll(deleteOrder.getBelongWho());
	}

	@DeleteMapping("/deleteOne")
	@ResponseBody
	public int deleteOne(@RequestBody DeleteOrder deleteOrder){
		if (deleteOrder == null){
			return -1;
		}
		return odListService.deleteOne(deleteOrder.getId());
	}

	@PostMapping("/editOne")
	@ResponseBody
	public OdList editOne(@RequestBody EditOrder editOrder){
		if (editOrder == null){
			return null;
		}
		return odListService.updateOne(editOrder);
	}
}
