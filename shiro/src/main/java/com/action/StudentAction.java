package com.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.Student;
import com.service.StudentService;

@Controller

public class StudentAction {
	
	@Autowired
	private StudentService  studentService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/addStudent")
	@ResponseBody
	public String addStudent()
	{
		return "添加学生成功";
	}
	
	@RequestMapping("/showStudent")
	public String  search(Page<Student> page, @RequestParam(defaultValue="-1") Integer status,Integer xh, String xm, String minDate,String maxDate)
	
	{ 
		
		/*
	    IPage i;
	    i.getTotal();//总条数
	    i.getCurrent();//当前页
	    i.getPages();//总页数
	    i.getSize();//页大小
	    i.getRecords();
	    */
		
		
	    
		IPage<Student> result =  studentService.search(page, status, xh, xm, minDate, maxDate);
	    request.setAttribute("result", result);
	    
	    long count = result.getTotal()%page.getSize()==0 ? result.getTotal()/page.getSize():result.getTotal()/page.getSize()+1;
	   
	    if (result.getCurrent()<1) result.setCurrent(1);
	    if (result.getCurrent()>=count)
	    	result.setCurrent(count);
	    
	    request.setAttribute("count", count);
	    
	    
	    return "/showStudent.jsp"; //redirect:
	}
	
	
	
	@RequestMapping("/showStudent2")
	public String  search2(Page<Student> page, @RequestParam(defaultValue="-1") Integer status,Integer xh, String xm, String minDate,String maxDate)
	
	{ 
		
		/*
	    IPage i;
	    i.getTotal();//总条数
	    i.getCurrent();//当前页
	    i.getPages();//总页数
	    i.getSize();//页大小
	    i.getRecords();
	    */
		
		
	    
		IPage<Student> result =  studentService.search(page, status, xh, xm, minDate, maxDate);
	    request.setAttribute("result", result);
	    
	    long count = result.getTotal()%page.getSize()==0 ? result.getTotal()/page.getSize():result.getTotal()/page.getSize()+1;
	   
	    if (result.getCurrent()<1) result.setCurrent(1);
	    if (result.getCurrent()>=count)
	    	result.setCurrent(count);
	    
	    request.setAttribute("count", count);
	    
	    
	    return "/showStudent2.jsp"; //redirect:
	}
	
	
	
	@RequestMapping("/changeStatus/{xh}")
	@ResponseBody
	public String changeStatus(@PathVariable int xh)
	{
		 //修改状态，返回修改后的状态 
		  Student  stu = studentService.changeStatus(xh);
		  if (stu!=null)
			  return stu.getStatus()+"";
		  return "";
	}
	
	/**
	 * 批量修改状态
	 * @param ids 要修改的学号  -1,1,2
	 * @param status 1要改成启用  0改成禁用
	 * @return
	 */
	@RequestMapping("/changeStatus2")
	@ResponseBody
	public String changeStatus2(String ids,int  status)
	{
		//修改状态，返回修改后的状态 
		  int n = studentService.changeStatus2(ids,status);
		  return n+"";
	}
	
	
	@RequestMapping("/toUpdateView/{xh}")
	public String toUpdateView(@PathVariable("xh") int  xh)
	{
		Student stu = studentService.findById(xh);
		request.setAttribute("stu", stu);
		return "/updateStudentView.jsp";
	}

}






