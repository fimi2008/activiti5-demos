package com.lionxxw.erp.controller;

import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.utils.PropertiesUtil;
import com.lionxxw.employee.dto.EmployeeDto;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


public class BaseController {
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("leftUrl",getRequest().getRequestURI());
		mv.addObject("pId",getRequest().getParameter("pId"));
		mv.addObject("wwwdomain", PropertiesUtil.getProperty("wwwdomain"));
		EmployeeDto employeeDto = getSessionEmp();
		mv.addObject("login_emp", employeeDto);
		return mv;
	}
	
	public EmployeeDto getSessionEmp(){
		EmployeeDto emp = (EmployeeDto)getRequest().getSession().getAttribute(DataStatus.SESSION_EMP);
		return emp;
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}
}