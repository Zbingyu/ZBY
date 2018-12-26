package com.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.Admin;
import com.pojo.Power;
import com.service.PowerService;

public class PowerRealm extends AuthorizingRealm {
	
	@Autowired
	private PowerService  powerService;

	/**
	 * 注册当前用户的权限(名称)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		//得当前已经登录的用户名
		String username = (String)pc.getPrimaryPrincipal();
		
		//得当前登录用户的权限
		List<Power>  list  = powerService.findPowerByUser(username);
		
		SimpleAuthorizationInfo  info = new SimpleAuthorizationInfo();
		for (Power p:list)
		{
			String name = p.getName();
			info.addStringPermission(name);
		}
		
		return info;
	}

	/**
	 *  登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken  token) throws AuthenticationException {
		
		//得到输入的用户名和密码
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		
		//查数据库
		Admin admin = powerService.doLogin(username, password);
		if (admin==null)
		         return null;
		
		//登录
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
		return info;
	}

}










