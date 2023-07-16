package com.example.ordermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordermanagement.domain.OdList;
import com.example.ordermanagement.domain.OdUser;
import com.example.ordermanagement.domain.request.RegisterUser;
import com.example.ordermanagement.service.OdUserService;
import com.example.ordermanagement.mapper.OdUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class OdUserServiceImpl extends ServiceImpl<OdUserMapper, OdUser>
    implements OdUserService{

	@Resource
	private OdUserMapper odUserMapper;

	/**
	 * 混淆密码
	 */
	public static final String SALT = "hw_lee";

	/**
	 * 用户登录键
	 */
	public static final String USER_LOGIN_STATUS = "userLoginStatus";

	@Override
	public long userRegister(RegisterUser registerUser) {
		//校验
		String registerAc = registerUser.getUserAccount();
		String registerNa = registerUser.getUserName();
		String registerPa = registerUser.getUserPassword();
		String registerCheck = registerUser.getUserCheckPassword();
		String registerPh = registerUser.getUserPhone();
		int tempGender = 1;
		if (!registerUser.getUserGender().equals("man")){
			tempGender = 0;
		}
		int registerGen = tempGender;
		if (StringUtils.isAnyBlank(registerAc,registerNa,registerPa,
				registerCheck,registerPh)){
			return -1;
		}
		//账号格式校验
		String pattenAc = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,8}$";
		Matcher matcherAc = Pattern.compile(pattenAc).matcher(registerAc);
		if (!matcherAc.find()){
			return -1;
		}
		//用户名格式校验
		String pattenNa = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,12}$";
		Matcher matcherNa = Pattern.compile(pattenNa).matcher(registerNa);
		if (!matcherNa.find()){
			return -1;
		}
		//密码格式校验
		String pattenPa = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,18}$";
		Matcher matcherPa = Pattern.compile(pattenPa).matcher(registerPa);
		if (!matcherPa.find()){
			return -1;
		}
		//确认密码校验
		if (!registerCheck.equals(registerPa) ){
			return -1;
		}
		//手机号格式校验
		String pattenPh = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
		Matcher matcherPh = Pattern.compile(pattenPh).matcher(registerPh);
		if (!matcherPh.find()){
			return -1;
		}

		//账号不能重复
		QueryWrapper<OdUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("userAccount",registerAc);
		long count = this.count(queryWrapper);
		if (count > 0 ){
			return -1;
		}
		//加密
		String encryptPassword = DigestUtils.md5DigestAsHex((SALT+registerPa).getBytes());

		//插入数据
		OdUser odUser = new OdUser();
		odUser.setUserAccount(registerAc);
		odUser.setUserName(registerNa);
		odUser.setUserPassword(encryptPassword);
		odUser.setUserPhone(registerPh);
		odUser.setGender(registerGen);
		boolean saveResult = this.save(odUser);
		if (!saveResult){
			return -1;
		}
		return odUser.getId();
	}

	@Override
	public OdUser userLogin(String userAccount, String userPassword, HttpServletRequest request) {
		//校验
		if (StringUtils.isAnyBlank(userAccount,userPassword)){
			return null;
		}
		//账号格式校验
		String pattenAc = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,8}$";
		Matcher matcherAc = Pattern.compile(pattenAc).matcher(userAccount);
		if (!matcherAc.find()){
			return null;
		}
		//密码格式校验
		String pattenPa = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,18}$";
		Matcher matcherPa = Pattern.compile(pattenPa).matcher(userPassword);
		if (!matcherPa.find()){
			return null;
		}

		//加密
		String encryptPassword = DigestUtils.md5DigestAsHex((SALT+userPassword).getBytes());

		//查询用户是否存在
		QueryWrapper<OdUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("userAccount",userAccount);
		queryWrapper.eq("userPassword",encryptPassword);
		OdUser odUser = odUserMapper.selectOne(queryWrapper);
		if (odUser == null ){
			return null;
		}
		//脱敏
		OdUser safetyUser = new OdUser();
		safetyUser.setId(odUser.getId());
		safetyUser.setUserAccount(odUser.getUserAccount());
		safetyUser.setUserName(odUser.getUserName());
		safetyUser.setUserPhone(odUser.getUserPhone());
		safetyUser.setGender(odUser.getGender());
		safetyUser.setIsAdmin(odUser.getIsAdmin());

		//记录用户登陆态
		request.getSession().setAttribute(USER_LOGIN_STATUS,odUser);
		return safetyUser;
	}

	@Override
	public int userChangePass(long UserId, String newPass) {
		if (StringUtils.isAnyBlank(newPass)){
			return -1;
		}
		//密码格式校验
		String pattenPa = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,18}$";
		Matcher matcherPa = Pattern.compile(pattenPa).matcher(newPass);
		if (!matcherPa.find()){
			return -1;
		}

		//加密
		String encryptPassword = DigestUtils.md5DigestAsHex((SALT+newPass).getBytes());

		//加密后的密码存入数据库中
		OdUser tempUser = new OdUser();
		tempUser.setId(UserId);
		tempUser.setUserPassword(encryptPassword);

		return odUserMapper.updateById(tempUser);


	}

	@Override
	public List<OdUser> selectAllUser() {
		QueryWrapper<OdUser> queryWrapper = new QueryWrapper<>();
		int i =0;
		queryWrapper.eq("isAdmin",i);
		List<OdUser> selectRes = odUserMapper.selectList(queryWrapper);
		return selectRes;
	}
}




