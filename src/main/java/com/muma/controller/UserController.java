package com.muma.controller;

import com.muma.common.Session;
import com.muma.controller.base.BaseResult;
import com.muma.dto.ShareUserDto;
import com.muma.dto.UserInfoDto;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.service.UserService;
import com.muma.util.Authenticate;
import com.muma.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import static com.muma.common.HttpContext.getRequset;
import static com.muma.common.HttpContext.getResponse;

@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	/**
	 * 用户登录
	 * @return
	 */
	@RequestMapping(value = "login.action" ,method = RequestMethod.POST)
	@Authenticate(permissions = "0,1,2")
	@ResponseBody
	public BaseResult login(){
		String regPhone = getRequset().getParameter("regPhone");
		String password = getRequset().getParameter("password");
		String uniqueId = getRequset().getParameter("uniqueId");
		try{
			logger.info("=====================用户名："+regPhone+"密码："+password+"登录时间："+ TimeUtils.getStringDate()+"==================");
			UserInfoDto userInfo = userService.login(regPhone,password,uniqueId);
			Session.loginUser(getRequset().getSession(), userInfo);
			return new BaseResult(true,userInfo);
		}catch (BizException e){
			return new BaseResult(false,e.getMessage());
		} catch (Exception e){
			logger.error("登录异常：{}",e);
			return new BaseResult(false,ResultEnum.INNER_ERROR.getMsg());
		}
	}
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping(value = "loginOut.action",method = RequestMethod.POST)
	@Authenticate(permissions = "0,1,2")
	@ResponseBody
	public BaseResult loginOut(){
		Session.loginoutUser(getRequset().getSession());
		return  new BaseResult(true,"登出成功！");
	}
	/**
	 * 注册用户
	 * @return
	 */
	@RequestMapping(value = "register.action",method = RequestMethod.POST)
	@Authenticate(permissions = "0,1,2")
	@ResponseBody
	public BaseResult register(){
		String regPhone = getRequset().getParameter("regPhone");
		String password = getRequset().getParameter("password");
		String code = getRequset().getParameter("code");
		String type = getRequset().getParameter("type");
		try{
			userService.register(regPhone,password,code,type);
			return new BaseResult(true,"注册成功！");
		}catch (BizException e){
			return new BaseResult(false,e.getMessage());
		} catch (Exception e){
			logger.error("注册异常：{}",e);
			return new BaseResult(false,ResultEnum.INNER_ERROR.getMsg());
		}
	}
	/**
	 * 邀请码校验
	 * @return
	 */
	@RequestMapping(value = "checkCode.action",method = RequestMethod.POST)
	@Authenticate(permissions = "0,2")
	@ResponseBody
	public BaseResult checkCode(){
		String code = getRequset().getParameter("code");
		try{
			userService.checkShareCode(code);
			return new BaseResult(true,"邀请码有效！");
		}catch (BizException e){
			return new BaseResult(false,e.getMessage());
		} catch (Exception e){
			logger.error("邀请码校验异常：{}",e);
			return new BaseResult(false,ResultEnum.INNER_ERROR.getMsg());
		}
	}
	/**
	 * 上传用户认证详细信息
	 * @return
	 */
	@RequestMapping(value = "authInfo.action",method = RequestMethod.POST)
	@Authenticate(permissions = "0,2")
	@ResponseBody
	public BaseResult authInfo(@RequestParam("idImageWhite") MultipartFile idImageWhite,
							   @RequestParam("idImageBlack") MultipartFile idImageBlack){
		String idNumber = getRequset().getParameter("idNumber");
		String idName = getRequset().getParameter("idName");
		String bankNumber = getRequset().getParameter("bankNumber");
		String bankPhone = getRequset().getParameter("bankPhone");
		try{
			UserInfoDto userInfoDto= (UserInfoDto) Session.getSessionAttribute();
			userService.updateUserDetail(idImageWhite,idImageBlack,userInfoDto.getRegPhone(),idNumber,idName,bankNumber,bankPhone);
			return new BaseResult(true,"用户认证保存成功！");
		}catch (BizException e){
			return new BaseResult(false,e.getMessage());
		} catch (Exception e){
			logger.error("用户认证保存异常：{}",e);
			return new BaseResult(false,ResultEnum.INNER_ERROR.getMsg());
		}
	}

	/**
	 * 查看邀请的人数
	 * @return
	 */
	@RequestMapping(value = "shareUsers.action",method = RequestMethod.POST)
	@Authenticate(permissions = "0,2")
	@ResponseBody
	public BaseResult shareUser(){
		String regPhone = getRequset().getParameter("regPhone");
		try{
			List<ShareUserDto> shareUserDtos = userService.shareUser(regPhone);
			return new BaseResult(true, shareUserDtos);
		}catch (BizException e){
			return new BaseResult(false,e.getMessage());
		} catch (Exception e){
			logger.error("邀请码校验异常：{}",e);
			return new BaseResult(false,ResultEnum.INNER_ERROR.getMsg());
		}
	}


}
