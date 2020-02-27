package com.muma.service.impl;

import com.muma.controller.base.BaseResult;
import com.muma.dao.BuyerDao;
import com.muma.entity.Buyer;
import com.muma.entity.User;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.service.UserService;
import com.muma.dao.UserDao;
import com.muma.util.Precondition;
import com.muma.util.VaildUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;
	@Autowired
	private BuyerDao buyerDao;

	/**
	 * 用户登录
	 * @param regPhone
	 * @param pwd
	 * @return
	 */
	@Override
	public User login(String regPhone, String password) {
		Precondition.checkState(StringUtils.isNotBlank(regPhone), "regPhone is null!");
		Precondition.checkState(StringUtils.isNotBlank(password), "password is null!");
		User user = userDao.queryByPhoneAndPwd(regPhone,password);
		Precondition.checkNotNull(user, ResultEnum.INVALID_USER.getMsg());
		return user;
	}

	/**
	 * 注册
	 * @param regPhone
	 * @param password
	 * @param type
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void register(String regPhone, String password, String type) {
		Precondition.checkState(StringUtils.isNotBlank(regPhone), "regPhone is null!");
		Precondition.checkState(StringUtils.isNotBlank(password), "password is null!");
		Precondition.checkState(StringUtils.isNotBlank(type), "type is null!");
		Boolean isRight = VaildUtils.checkPhone(regPhone);
		Precondition.checkState(isRight,"手机号码错误！");
		User user = userDao.queryByPhone(regPhone);
		Precondition.checkState(user !=null, "该手机号码已经注册过！");
		//保存用户登录信息和详细信息
		User userInfo = new User();


	}


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

	@Override
	public List<Buyer> getBuyerList() {
		return buyerDao.queryBuyerList();
	}


}
