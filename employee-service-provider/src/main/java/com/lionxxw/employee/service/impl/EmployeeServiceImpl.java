package com.lionxxw.employee.service.impl;

import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtil;
import com.lionxxw.common.utils.ExceptionUtil;
import com.lionxxw.common.utils.ObjectUtil;
import com.lionxxw.employee.dao.EmpDepTempDao;
import com.lionxxw.employee.dao.EmpDepViewDao;
import com.lionxxw.employee.dao.EmployeeDao;
import com.lionxxw.employee.dto.EmployeeDto;
import com.lionxxw.employee.entity.EmpDepTemp;
import com.lionxxw.employee.entity.EmpDepView;
import com.lionxxw.employee.entity.Employee;
import com.lionxxw.employee.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Description: 员工接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 下午8:27
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private EmpDepTempDao empDepTempDao;
    @Autowired
    private EmpDepViewDao empDepViewDao;

    /**
     * 保存员工对象
     * @param obj 数据对象
     * @return EmployeeDto
     * @throws Exception
     */
    public EmployeeDto save(EmployeeDto obj) throws Exception {
        ExceptionUtil.checkObjIsNull(obj);
        Employee employee =BeanUtil.createBeanByTarget(obj, Employee.class);
        employeeDao.insertSelective(employee);
        obj.setId(employee.getId());
        obj.setCreateTime(employee.getCreateTime());
        return obj;
    }

    /**
     * 物理删除员工对象
     * @param id 主键id
     * @return boolean
     * @throws Exception
     */
    public boolean delById(Long id) throws Exception {
        ExceptionUtil.checkIdIsNull(id, this.getClass(), "delById");
        employeeDao.deleteByPrimaryKey(id);
        return true;
    }

    /**
     * 更新员工对象
     * @param obj 数据对象
     * @throws Exception
     */
    public void update(EmployeeDto obj) throws Exception {
        ExceptionUtil.checkObjIsNull(obj);
        ExceptionUtil.checkIdIsNull(obj.getId(), this.getClass(), "update");
        Employee employee =BeanUtil.createBeanByTarget(obj, Employee.class);
        employeeDao.updateByPrimaryKeySelective(employee);
    }

    /**
     * 根据主键id查询员工对象
     * @param id 主键id
     * @return
     * @throws Exception
     */
    public EmployeeDto getById(Long id) throws Exception {
        ExceptionUtil.checkIdIsNull(id, this.getClass(), "getById");
        EmpDepView employee = empDepViewDao.selectByPrimaryKey(id);
        ExceptionUtil.checkObjNotExist(employee);
        EmployeeDto dto =BeanUtil.createBeanByTarget(employee, EmployeeDto.class);
        return dto;
    }

    /**
     * 根据参数查询员工对象
     * @param obj 对象
     * @return
     * @throws Exception
     */
    public List<EmployeeDto> queryByParam(EmployeeDto obj) throws Exception {
        List<EmpDepView> employees = empDepViewDao.queryByParam(obj, null);
        if (ObjectUtil.notEmpty(employees)){
            List<EmployeeDto> dtos = BeanUtil.createBeanListByTarget(employees, EmployeeDto.class);
            return dtos;
        }
        return null;
    }

    /**
     * 根据参数分页查询员工对象
     * @param obj 对象
     * @param query 分页查询参数
     * @return
     * @throws Exception
     */
    public PageResult<EmployeeDto> queryByPage(EmployeeDto obj, PageQuery query) throws Exception {
        int total = empDepViewDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<EmpDepView> employees = empDepViewDao.queryByParam(obj, query);
            List<EmployeeDto> dtos = BeanUtil.createBeanListByTarget(employees, EmployeeDto.class);
            return new PageResult<EmployeeDto>(query, dtos);
        }

        return null;
    }

    @Transactional
	public void save(EmployeeDto dto, Long depId) throws Exception {
		save(dto);
		saveEmpDepTemp(dto.getId(), depId);
	}

    @Transactional
	public void update(EmployeeDto dto, Long depId) throws Exception {
		update(dto);
		empDepTempDao.delByEmpId(dto.getId());
		saveEmpDepTemp(dto.getId(), depId);
	}
    
    /**
     * 保存员工部门对应关系
     * @param empId
     * @param depId
     * @author xiang_wang
     * 2016年5月10日下午2:05:50
     */
    private void saveEmpDepTemp(Long empId, Long depId) {
		EmpDepTemp temp = new EmpDepTemp();
		temp.setEmpId(empId);
		temp.setDepId(depId);
		empDepTempDao.insertSelective(temp, false);
	}
}