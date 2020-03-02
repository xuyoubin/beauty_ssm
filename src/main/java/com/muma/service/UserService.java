package com.muma.service;

import java.util.List;

import com.muma.dto.UserInfoDto;
import com.muma.entity.Buyer;
import com.muma.entity.User;

public interface UserService {
	/**
	 * 用户登录
	 * @param regPhone
	 * @param password
	 * @return
	 */
	UserInfoDto login(String regPhone, String password);
	/**
	 * 用户注册
	 * @param regPhone
	 * @param password
	 * @return
	 */
	void register(String regPhone,String password,String code,String type);
	/**
	 * 检查验证码是否有效
	 * @return
	 */
	public void checkShareCode(String code);
	/**
	 * 更新用户认证信息
	 * @return
	 */
	public void updateUserDetail(String code);

}
