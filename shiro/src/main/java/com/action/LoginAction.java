package com.action;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAction {
	
	@RequestMapping("/logout")
	public String logout()
	{
		SecurityUtils.getSubject().logout();
		return "/login.jsp";
	}

	@RequestMapping("/login.do")
	public String login(String username, String password) {
		// 获取当前用户
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated() == false) // 没有登录
		{
			System.out.println("没有认证");
			try {
				UsernamePasswordToken   token = new UsernamePasswordToken(username,password);
                   currentUser.login( token);
                  // currentUser.getSession().setAttribute("username", username);
                   return "/main.jsp";
			} catch (Exception e) {
				   return "/login.jsp";
			}
			
		} else {
			System.out.println("已认证");
			return "/main.jsp";
		}

	}

}
