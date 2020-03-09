package com.muma.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.muma.controller.base.BaseResult;
import com.muma.dao.BuyerDao;
import com.muma.dao.UserDao;
import com.muma.dao.UserDetailDao;
import com.muma.dto.ShareUserDto;
import com.muma.dto.UserInfoDto;
import com.muma.entity.Buyer;
import com.muma.entity.User;
import com.muma.entity.UserDetail;
import com.muma.enums.RoalEnum;
import com.muma.enums.SexEnum;
import com.muma.enums.StatusEnum;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.service.UserService;
import com.muma.util.BankNameUtil;
import com.muma.util.IPUtil;
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
	@Transactional(rollbackFor = Exception.class)
	@Override
	public UserInfoDto login(String regPhone, String password,String uniqueId) {
		Precondition.checkState(StringUtils.isNotBlank(regPhone), "regPhone is null!");
		Precondition.checkState(StringUtils.isNotBlank(password), "password is null!");
		Precondition.checkState(StringUtils.isNotBlank(uniqueId), "uniqueId is null!");
		//获取本机公网IP，防止多用户在同一个公网IP登录
		String publicIp = IPUtil.getV4IP();
		User u = userDao.queryByIp(publicIp);
		if(u != null){
			Precondition.checkState(regPhone.equals(u.getRegPhone()) , "网络已经被占用，请切换网络连接!");
		}
		// TODO 设备唯一识别号验证
        u = userDao.queryByUniqueId(uniqueId);
		if(u != null){
			Precondition.checkState(regPhone.equals(u.getRegPhone()) , "该设备已经有账号登录!");
		}
		User user = userDao.queryByPhoneAndPwd(regPhone,password);
		Precondition.checkNotNull(user, ResultEnum.INVALID_USER.getMsg());
		// 插入IP地址
		user.setIpAddress(publicIp);
		user.setUniqueId(uniqueId);
		user.setUpdateBy(regPhone);
		userDao.updateUser(user);
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
		//查看身份证是否注册过
		UserDetail user = userDetailDao.queryByCardId(idNumber);
		Precondition.checkNotNull(user, "该身份证号已经注册过!");
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
		if( StatusEnum.CONFIRM_PASS.equals(userInfo.getStatus()) || StatusEnum.USER_BLACK.equals(userInfo.getStatus()) ||
				StatusEnum.CONFIRM_WAIT.equals(userInfo.getStatus())){
			throw new BizException("当前用户状态不可修改认证信息!");
		}
		//保存正面
		String whiteUrl = UploadImageUtil.saveImage(idImageWhite,UploadImageUtil.IMAGE_TYPE_USER_INFO,regPhone,"身份证正面");
		//保存反面
		String blackUrl = UploadImageUtil.saveImage(idImageBlack,UploadImageUtil.IMAGE_TYPE_USER_INFO,regPhone,"身份证反面");
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
	 * 查询用户邀请的人信息
	 * @param regPhone
	 * @return
	 */
	public List<ShareUserDto> shareUser(String regPhone){
		//查询邀请次数
		List<ShareUserDto> shareUserDtos = userDetailDao.queryByParentPhone(regPhone);
		// TODO 查询有效的销售任务
		return shareUserDtos;
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
		List<ShareUserDto> shareUserDtos = userDetailDao.queryByParentPhone(parentDetail.getRegPhone());
		if(RoalEnum.BUYER_ROAL.equals(parentDetail.getRoalId())){ //上级是买家
			Precondition.checkState(shareUserDtos.size()<=6, "该邀请码无效！");
			// TODO 查询有效1交易次数

		}else if(RoalEnum.BUSINESS_ROAL.equals(parentDetail.getRoalId())){//上级是商家
			Precondition.checkState(false, "该邀请码无效！");
		}else {//上级是平台
			Precondition.checkState(shareUserDtos.size()<=50, "该邀请码无效！");
		}
		return parentDetail;
	}



}
