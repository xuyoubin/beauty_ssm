package com.muma.service.impl;

import com.muma.entity.User;
import com.muma.service.UserService;
import com.muma.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;
	
	
	@Override
	public List<User> getUserList(int offset, int limit) {
		//先去缓存中取
		List<User> result_cache=null;
		if(result_cache==null){
			//缓存中没有再去数据库取，并插入缓存（缓存时间为60秒）
			result_cache=userDao.queryAll(offset, limit);
			LOG.info("put cache with key:");
		}else{
			LOG.info("get cache with key:");
		}
		return result_cache;
	}
	
	

}
