package com.muma.common;


import com.muma.dto.UserInfoDto;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class PathFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession  session = req.getSession();
		String requestUri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String requestPath = requestUri.substring(contextPath.length());
		requestPath = formatRequestPath(requestPath);
		UserInfoDto userInfo = null;
		userInfo = (UserInfoDto) session.getAttribute(Session.USER_KEY);
		
		if(requestPath.indexOf("/user/register")>=0 || requestPath.indexOf("/user/login")>=0 ){ //登录页面,注册页面不拦截
			chain.doFilter(req, res);
		}else{
			if(userInfo == null){//没有用户信息则跳转
				req.getRequestDispatcher("/user/loginPage.do").forward(req, res);
				return;
			}else{
				chain.doFilter(req, res);
				return;
			}
		}
		
		
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private String formatRequestPath(String path){
		String[] s = path.split("/");
		StringBuffer result = new StringBuffer();
		for(String c:s){
			if(!"".equals(c.trim())){
				result.append("/").append(c);
			}
		}
		return result.toString();
	}

	

}
