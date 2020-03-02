package com.muma.common;



import com.muma.dto.UserInfoDto;
import com.muma.util.LoginType;
import javax.servlet.http.HttpSession;

public class Session {
	
	public static final String USER_KEY = "USER_KEY";

	/**
	 * 用户登录成功保存session
	 * @param session
	 * @param userInfo
	 */
	public static void loginUser(HttpSession session, UserInfoDto userInfo){
		session.setAttribute(USER_KEY, userInfo);
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
		UserInfoDto userInfo = (UserInfoDto) session.getAttribute(USER_KEY);
		if(null == userInfo){
			return LoginType.LOGIN_TIMEOUT;
		}
		return LoginType.LOGINED;
	}
	

}
