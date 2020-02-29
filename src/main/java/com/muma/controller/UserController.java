package com.muma.controller;

import com.muma.common.HttpContext;
import com.muma.common.Session;
import com.muma.controller.base.BaseResult;
import com.muma.entity.Buyer;
import com.muma.entity.User;
import com.muma.enums.base.ResultEnum;
import com.muma.exception.BizException;
import com.muma.service.UserService;
import com.muma.util.Precondition;
import com.muma.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

import static com.muma.common.HttpContext.getRequset;

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
	@RequestMapping(value = "login.do")
	@ResponseBody
	public BaseResult<User> login(){
		String regPhone = getRequset().getParameter("regPhone");
		String password = getRequset().getParameter("password");
		try{
			logger.info("=====================用户名："+regPhone+"密码："+password+"登录时间："+ TimeUtils.getTime(new Date())+"==================");
			User userInfo = userService.login(regPhone,password);
			Session.loginUser(getRequset().getSession(), userInfo);
			return new BaseResult<User>(true,userInfo);
		}catch (BizException e){
			return new BaseResult<User>(false,e.getMessage());
		} catch (Exception e){
			logger.error("登录异常：{}",e);
			return new BaseResult<User>(false,ResultEnum.INNER_ERROR.getMsg());
		}
	}
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping(value = "loginOut.do")
	@ResponseBody
	public BaseResult<User> loginOut(){
		Session.loginoutUser(getRequset().getSession());
		return  new BaseResult<User>(true,"登出成功！");
	}

	/**
	 * 注册用户
	 * @return
	 */
	@RequestMapping(value = "register.do")
	@ResponseBody
	public BaseResult<User> register(){
		String regPhone = getRequset().getParameter("regPhone");
		String password = getRequset().getParameter("password");
		String type = getRequset().getParameter("type");
		userService.register(regPhone,password,type);

		return  new BaseResult<User>(true,"登出成功！");
	}








	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer offset, Integer limit) {
		logger.info("invoke----------/user/list");
		offset = offset == null ? 0 : offset;//默认便宜0
		limit = limit == null ? 50 : limit;//默认展示50条
		List<User> list = userService.getUserList(offset, limit);
		model.addAttribute("userlist", list);
		return "userlist";
	}

	@RequestMapping(value = "/blist", method = RequestMethod.GET)
	public String blist(Model model, Integer offset, Integer limit) {
		logger.info("invoke----------/user/list");
		List<Buyer> list = userService.getBuyerList();
		model.addAttribute("userlist", list);
		return "userlist";
	}

}
