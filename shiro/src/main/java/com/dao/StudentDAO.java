package com.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.Student;

public interface StudentDAO  extends BaseMapper<Student>{
   	
	   public int  batchUpdateStatus(@Param("ids") String ids, @Param("status") int status);

}
