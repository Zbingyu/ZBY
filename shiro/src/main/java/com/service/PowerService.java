package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.AdminDAO;
import com.dao.PowerDAO;
import com.dao.RoleDAO;
import com.dao.RolePowerDAO;
import com.dao.UserRoleDAO;
import com.pojo.Admin;
import com.pojo.Power;

/**
 * 权限管理
 * 
 * @author lgh-pc
 *
 */

@Service
public class PowerService {

	@Autowired
	private AdminDAO adminDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private PowerDAO powerDAO;

	@Autowired
	private UserRoleDAO userRoleDAO;

	@Autowired
	private RolePowerDAO rolePowerDAO;

	/**
	 * 管理员登录 
	 * @param username
	 * @param password
	 * @return  null登录失败
	 */
	public Admin doLogin(String username, String password) {
		QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>();
		queryWrapper.eq(true, "username", username).eq(true, "password", password);
		List list = adminDAO.selectList(queryWrapper);
		if (list != null && list.size() > 0)
			return (Admin) list.get(0);
		return null;
	}
	
	/**
	 * 查所有权限
	 * @return
	 */
	public List<Power>  findAllPower()
	{
		return powerDAO.selectList(null);
	}
	
	/**
	 * 查单个用户的权限
	 * @param username
	 * @return
	 */
	public List<Power> findPowerByUser(String username)
	{
		return adminDAO.getPowerByUser(username);
	}
 
}
