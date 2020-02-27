package com.muma.service;

import java.util.List;

import com.muma.entity.Buyer;
import com.muma.entity.User;

public interface UserService {
	/**
	 * 用户登录
	 * @param regPhone
	 * @param pwd
	 * @return
	 */
	User login(String regPhone,String pwd);

	void register(String regPhone,String pwd,String type);


	List<User> getUserList(int offset, int limit);

	List<Buyer> getBuyerList();
	 
}
