package com.muma.service;

import java.util.List;

import com.muma.entity.User;

public interface UserService {

	List<User> getUserList(int offset, int limit);
	 
}
