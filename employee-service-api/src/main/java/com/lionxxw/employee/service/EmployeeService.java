package com.lionxxw.employee.service;

import com.lionxxw.common.base.BaseService;
import com.lionxxw.employee.dto.EmployeeDto;

/**
 * <p>Description: 员工接口 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 下午7:56
 */
public interface EmployeeService extends BaseService<EmployeeDto>{

	/**
	 * 新增员工信息,并关联到部门
	 * @param dto 员工信息
	 * @param depId 部门id
	 * @throws Exception
	 * @author xiang_wang
	 * 2016年5月10日下午1:51:56
	 */
	void save(EmployeeDto dto, Long depId)throws Exception;
	
	/**
	 * 修改员工信息和部门关联信息
	 * @param dto 员工信息
	 * @param depId 部门id
	 * @throws Exception
	 * @author xiang_wang
	 * 2016年5月10日下午1:51:53
	 */
	void update(EmployeeDto dto, Long depId)throws Exception;
}
