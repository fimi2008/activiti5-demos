package com.lionxxw.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtil;
import com.lionxxw.common.utils.ExceptionUtil;
import com.lionxxw.common.utils.ObjectUtil;
import com.lionxxw.employee.dao.DepartmentDao;
import com.lionxxw.employee.dto.DepartmentDto;
import com.lionxxw.employee.dto.EmployeeDto;
import com.lionxxw.employee.entity.Department;
import com.lionxxw.employee.service.DepartmentService;

/**		
 * <p>Title: DepartmentServiceImpl </p>
 * <p>Description: 类描述:部门接口实现</p>
 * <p>Copyright (c) 2015 </p>
 * @author xiang_wang	
 * @date 2016年5月10日下午1:15:22
 * @version 1.0
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	public DepartmentDto save(DepartmentDto obj) throws Exception {
		ExceptionUtil.checkObjIsNull(obj);
		Department department =BeanUtil.createBeanByTarget(obj, Department.class);
        departmentDao.insertSelective(department);
        obj.setId(department.getId());
        obj.setCreateTime(department.getCreateTime());
        return obj;
	}

	public boolean delById(Long id) throws Exception {
		ExceptionUtil.checkIdIsNull(id, this.getClass(), "delById");
		departmentDao.deleteByPrimaryKey(id);
        return true;
	}

	public void update(DepartmentDto obj) throws Exception {
		ExceptionUtil.checkObjIsNull(obj);
        ExceptionUtil.checkIdIsNull(obj.getId(), this.getClass(), "update");
        Department department =BeanUtil.createBeanByTarget(obj, Department.class);
        departmentDao.updateByPrimaryKeySelective(department);
	}

	public DepartmentDto getById(Long id) throws Exception {
		ExceptionUtil.checkIdIsNull(id, this.getClass(), "getById");
		Department department = departmentDao.selectByPrimaryKey(id);
        ExceptionUtil.checkObjNotExist(department);
        DepartmentDto dto =BeanUtil.createBeanByTarget(department, EmployeeDto.class);
        return dto;
	}

	public List<DepartmentDto> queryByParam(DepartmentDto obj) throws Exception {
		List<Department> departments = departmentDao.queryByParam(obj, null);
        if (ObjectUtil.notEmpty(departments)){
            List<DepartmentDto> dtos = BeanUtil.createBeanListByTarget(departments, DepartmentDto.class);
            return dtos;
        }
        return null;
	}

	public PageResult<DepartmentDto> queryByPage(DepartmentDto obj,
			PageQuery query) throws Exception {
		int total = departmentDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Department> departments = departmentDao.queryByParam(obj, query);
            List<DepartmentDto> dtos = BeanUtil.createBeanListByTarget(departments, EmployeeDto.class);
            return new PageResult<DepartmentDto>(query, dtos);
        }

        return null;
	}
}