package com.muma.common;



import com.muma.entity.User;
import com.muma.util.LoginType;

import javax.servlet.http.HttpSession;

public class Session {
	
	public static final String USER_KEY = "USER_KEY";

	/**
	 * 用户登录成功保存session
	 * @param session
	 * @param user
	 */
	public static void loginUser(HttpSession session, User user){
		session.setAttribute(USER_KEY, user);
	}
	
	/**
	 * 清除用户session
	 * @param session
	 */
	public static void loginoutUser(HttpSession session){
		if(null != session){
			session.removeAttribute(USER_KEY);
		}
	}
	
	/**
	 * 判断用户是否已经登录
	 * @param session
	 * @return
	 */
	public static int isLogin(HttpSession session){
		if(null == session){
			return LoginType.NO_LOGIN;

		}
		User user = (User) session.getAttribute(USER_KEY);
		if(null == user){
			return LoginType.LOGIN_TIMEOUT;
		}
		return LoginType.LOGINED;
	}
	

}
