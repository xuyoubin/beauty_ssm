package com.muma.service.impl;

import com.muma.dao.BuyerDao;
import com.muma.dao.UserDao;
import com.muma.dao.UserDetailDao;
import com.muma.dto.UserInfoDto;
import com.muma.entity.Buyer;
import com.muma.entity.User;
import com.muma.entity.UserDetail;
import com.muma.enums.RoalEnum;
import com.muma.enums.base.ResultEnum;
import com.muma.service.UserService;
import com.muma.util.Precondition;
import com.muma.util.ShareCodeUtil;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;
	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private UserDetailDao userDetailDao;

	/**
	 * 用户登录
	 * @param regPhone
	 * @param password
	 * @return
	 */
	@Override
	public UserInfoDto login(String regPhone, String password) {
		Precondition.checkState(StringUtils.isNotBlank(regPhone), "regPhone is null!");
		Precondition.checkState(StringUtils.isNotBlank(password), "password is null!");
		User user = userDao.queryByPhoneAndPwd(regPhone,password);
		Precondition.checkNotNull(user, ResultEnum.INVALID_USER.getMsg());
		//根据手机号码查询用户详细信息
        UserInfoDto userInfo = userDetailDao.queryByRegPhone(user.getRegPhone());
		Precondition.checkNotNull(userInfo, "用户异常,请联系管理员！");
		//已经注册查询平台信息
		List<Buyer> buyerList = buyerDao.queryBuyerListByRegPhone(userInfo.getRegPhone());
		userInfo.setBuyerList(buyerList);
		return userInfo;
	}

	/**
	 * 注册
	 * @param regPhone
	 * @param password
	 * @param type
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void register(String regPhone, String password,String code, String type) {
		Precondition.checkState(StringUtils.isNotBlank(regPhone), "regPhone is null!");
		Precondition.checkState(StringUtils.isNotBlank(password), "password is null!");
		Precondition.checkState(StringUtils.isNotBlank(code), "code is null!");
		Precondition.checkState(StringUtils.isNotBlank(type), "type is null!");
		Boolean isRight = VaildUtils.checkPhone(regPhone);
		Precondition.checkState(isRight,"手机号码错误！");
		Integer userNum = userDao.queryByPhone(regPhone);
		Precondition.checkState(userNum == 0, "该手机号码已经注册过！");
		RoalEnum userRoal = RoalEnum.stateOf(Integer.valueOf(type));
		//注册买家查询邀请码是否有效
		String parentPhone = null;
		if(RoalEnum.BUYER_ROAL.equals(userRoal)){
			UserDetail parentDetail = checkCode(code);
			parentPhone = parentDetail.getRegPhone();
		}
		//保存用户登录信息和详细信息
		User user = new User();
		user.setRegPhone(regPhone);
		user.setPassword(password);
		user.setCreateBy(regPhone);
		userDao.addUser(user);
		UserDetail userDetail = new UserDetail();
		userDetail.setRegPhone(regPhone);
		userDetail.setRoalId(userRoal);
		userDetail.setParentPhone(parentPhone);
//		userDetail.setCode(ShareCodeUtil.toSerialCode(Long.valueOf(regPhone)));//获取自己的邀请码，用户审核通过的时候生成
		userDetail.setCreateBy(regPhone);
		userDetailDao.addUserDetail(userDetail);
	}

	/**
	 * 检查验证码是否有效
	 * 如果有效返回上级用户信息
	 * @param code
	 * @return
	 */
	private UserDetail checkCode(String code){
		Integer id = (int) ShareCodeUtil.codeToId(code);
		UserDetail parentDetail =  userDetailDao.queryByIdAndCode(id,code);
		Precondition.checkNotNull(parentDetail, "该邀请码无效！");
		//查询邀请次数
		if(RoalEnum.BUYER_ROAL.equals(parentDetail.getRoalId())){ //上级是买家
			Integer shareNum = userDetailDao.queryByParentPhone(parentDetail.getRegPhone());
			Precondition.checkState(shareNum<=6, "该邀请码无效！");
			//TODO查询有效1交易次数

		}else if(RoalEnum.BUSINESS_ROAL.equals(parentDetail.getRoalId())){//上级是商家
			Precondition.checkState(false, "该邀请码无效！");
		}else {//上级是平台
			Integer shareNum = userDetailDao.queryByParentPhone(parentDetail.getRegPhone());
			Precondition.checkState(shareNum<=50, "该邀请码无效！");
		}
		return parentDetail;
	}



}
