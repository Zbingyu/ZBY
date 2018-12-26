package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dao.StudentDAO;
import com.pojo.Student;

@Service
public class StudentService {
	
	@Autowired
	private StudentDAO studentDAO;
	
	
	public IPage<Student> search(Page<Student> page,Integer status, Integer xh, String xm, String minDate, String maxDate)
	{
		//page.setAsc("cs");
		QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>();
		if (xh!=null)
		{
			queryWrapper.eq(true, "xh", xh);
		}
		
		if (xm!=null && xm.length()>0)
		{
			queryWrapper.like(true, "xm", xm+"%");
			
		}
		
		if (minDate!=null && minDate.trim().length()>0)
		{
			queryWrapper.ge(true, "cs", minDate+" 00:00:00.000");
		}
		
		if (maxDate!=null && maxDate.trim().length()>0)
		{
			queryWrapper.le(true, "cs", maxDate+" 23:59:59.999");
		}
		
		if (status!=-1)
		{
			queryWrapper.eq(true, "status", status);
		}
		
		IPage<Student> result = studentDAO.selectPage(page,queryWrapper);
 		
		return result;
	}


	public Student changeStatus(int xh) {
		Student stu =  studentDAO.selectById(xh);
		if (stu==null)
		     return stu;
		
		Integer oldStatus = stu.getStatus();
		if (oldStatus==null)
			oldStatus =0 ;
		
		stu.setStatus(1-oldStatus);
		
		int n = studentDAO.updateById(stu);
		return stu;
	}


	/**
	 * 批量修改状态
	 * @param ids
	 * @param status
	 */
	public int changeStatus2(String ids, int status) {
	      int  n = studentDAO.batchUpdateStatus(ids, status);
		  return n;
	}


	public Student findById(int xh) {
		return studentDAO.selectById(xh);
	}
	
	

}











