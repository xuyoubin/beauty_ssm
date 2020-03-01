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

	void register(String regPhone,String password,String code,String type);


	List<User> getUserList(int offset, int limit);

	List<Buyer> getBuyerList();
	 
}
