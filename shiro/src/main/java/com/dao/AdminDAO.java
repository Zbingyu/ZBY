package com.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.Admin;
import com.pojo.Power;

public interface AdminDAO extends BaseMapper<Admin> {
	 public List<Power>  getPowerByUser(String username);
}
