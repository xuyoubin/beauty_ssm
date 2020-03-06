package com.muma.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.muma.controller.base.BaseResult;
import com.muma.dao.BuyerDao;
import com.muma.dao.UserDao;
import com.muma.dao.UserDetailDao;
import com.muma.dto.UserInfoDto;
import com.muma.entity.Buyer;
import com.muma.entity.User;
import com.muma.entity.UserDetail;
import com.muma.enums.RoalEnum;
import com.muma.enums.SexEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.base.ResultEnum;
import com.muma.service.UserService;
import com.muma.util.BankNameUtil;
import com.muma.util.IdcardUtils;
import com.muma.util.Precondition;
import com.muma.util.ShareCodeUtil;
import com.muma.util.UploadImageUtil;
import com.muma.util.VaildUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
		userDetail.setCreateBy(regPhone);
		userDetailDao.addUserDetail(userDetail);
	}
	/**
	 * 检查验证码是否有效
	 * @return
	 */
	@Override
	public void checkShareCode(String code) {
		Precondition.checkState(StringUtils.isNotBlank(code), "code is null!");
		checkCode(code);
	}
	/**
	 * 更新用户认证信息
	 * @return
	 */
	@Override
	public void updateUserDetail(MultipartFile idImageWhite, MultipartFile idImageBlack,
								 String regPhone, String idNumber, String idName, String bankNumber, String bankPhone) {
		Precondition.checkState(StringUtils.isNotBlank(regPhone), "regPhone is null!");
		Precondition.checkState(StringUtils.isNotBlank(idNumber), "请填写身份证号码!");
		Precondition.checkState(StringUtils.isNotBlank(idName), "请填写身份证姓名!");
		Precondition.checkState(StringUtils.isNotBlank(idImageWhite.getOriginalFilename()), "请上传身份证正面!");
		Precondition.checkState(StringUtils.isNotBlank(idImageBlack.getOriginalFilename()), "请上传身份证反面!");
		Precondition.checkState(StringUtils.isNotBlank(bankNumber), "请填写银行卡号!");
		Precondition.checkState(StringUtils.isNotBlank(bankPhone), "请填写绑定手机号码!");
        //验证身份证号码
		Precondition.checkState(IdcardUtils.validateCard(idNumber), "身份证号码格式有误!");
		//获取省份代码
		String provinceNum = IdcardUtils.getProvinceByIdCard(idNumber);
		//获取年龄
		int age = IdcardUtils.getAgeByIdCard(idNumber);
		int sex = IdcardUtils.getGenderByIdCard(idNumber);
		//验证银行卡号
		JSONObject  bankResult =  BankNameUtil.checkBankName(bankNumber);
		Precondition.checkState(bankResult.getBoolean("success"), bankResult.getString("message"));
		String bankName = bankResult.getString("message");
		//验证手机号码
		Boolean isRight = VaildUtils.checkPhone(bankPhone);
		Precondition.checkState(isRight,"手机号码错误！");
		//根据注册手机查询用户详细信息
		UserInfoDto userInfo = userDetailDao.queryByRegPhone(regPhone);
		Precondition.checkNotNull(userInfo, "用户异常,请联系管理员！");
		//保存照片
		//保存正面
		JSONObject result1 = UploadImageUtil.upImage(idImageWhite,UploadImageUtil.UPLOAD_IMAGE_TYPE_USER_INFO,regPhone);
		Precondition.checkState(result1.getBoolean("success"), result1.getString("message"));
		String whiteUrl = result1.getString("url");
		//保存反面
		JSONObject result2 = UploadImageUtil.upImage(idImageBlack,UploadImageUtil.UPLOAD_IMAGE_TYPE_USER_INFO,regPhone);
		Precondition.checkState(result2.getBoolean("success"), result2.getString("message"));
		String blackUrl = result2.getString("url");
		UserDetail userDetail = new UserDetail();
		userDetail.setId(userInfo.getId());
		userDetail.setIdCard(idNumber);
		userDetail.setRealName(idName);
		userDetail.setAge(age);
		userDetail.setSex(SexEnum.stateOf(sex));
		userDetail.setProvinceCode(provinceNum);
		userDetail.setBankId(bankNumber);
		userDetail.setBankName(bankName);
		userDetail.setPhone(bankPhone);
		userDetail.setCode(ShareCodeUtil.toSerialCode(Long.valueOf(userInfo.getId())));
		userDetail.setIdWhite(whiteUrl);
		userDetail.setIdBlack(blackUrl);
		userDetail.setUpdateBy(regPhone);
		userDetail.setStatus(StatusEnum.CONFIRM_WAIT);
		userDetailDao.updateUserDetail(userDetail);
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
		List<UserDetail> userDetails = userDetailDao.queryByParentPhone(parentDetail.getRegPhone());
		if(RoalEnum.BUYER_ROAL.equals(parentDetail.getRoalId())){ //上级是买家
			Precondition.checkState(userDetails.size()<=6, "该邀请码无效！");
			//TODO查询有效1交易次数

		}else if(RoalEnum.BUSINESS_ROAL.equals(parentDetail.getRoalId())){//上级是商家
			Precondition.checkState(false, "该邀请码无效！");
		}else {//上级是平台
			Precondition.checkState(userDetails.size()<=50, "该邀请码无效！");
		}
		return parentDetail;
	}



}
