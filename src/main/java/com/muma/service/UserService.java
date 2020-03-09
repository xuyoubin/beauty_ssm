package com.muma.service;

import com.muma.dto.ShareUserDto;
import com.muma.dto.UserInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
	/**
	 * 用户登录
	 * @param regPhone
	 * @param password
	 * @return
	 */
	UserInfoDto login(String regPhone, String password,String uniqueId);
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
	public void updateUserDetail(MultipartFile idImageWhite,MultipartFile idImageBlack,
								 String regPhone,String idNumber,String idName,String bankNumber,String bankPhone);

	/**
	 * 查询用户邀请的人信息
	 * @param regPhone
	 * @return
	 */
	public List<ShareUserDto> shareUser(String regPhone);
}
