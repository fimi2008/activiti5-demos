package com.lionxxw.employee.service.impl;

import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtil;
import com.lionxxw.common.utils.ExceptionUtil;
import com.lionxxw.common.utils.ObjectUtil;
import com.lionxxw.employee.dao.EmployeeDao;
import com.lionxxw.employee.dto.EmployeeDto;
import com.lionxxw.employee.entity.Employee;
import com.lionxxw.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Employee employee = employeeDao.selectByPrimaryKey(id);
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
        List<Employee> employees = employeeDao.queryByParam(obj, null);
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
        int total = employeeDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Employee> employees = employeeDao.queryByParam(obj, query);
            List<EmployeeDto> dtos = BeanUtil.createBeanListByTarget(employees, EmployeeDto.class);
            return new PageResult<EmployeeDto>(query, dtos);
        }

        return null;
    }
}