package com.lionxxw.employee.dao;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.employee.entity.EmpDepTemp;
import com.lionxxw.employee.entity.EmpDepTempExample;
import com.lionxxw.employee.mapper.EmpDepTempMapper;

/**
 * <p>Description: 员工部门对应关系dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 下午9:47
 */
@Repository
public class EmpDepTempDao extends MyBatisBaseDao<EmpDepTemp> {

    @Autowired
    @Getter
    private EmpDepTempMapper mapper;
    
    public void delByEmpId(Long empId){
    	EmpDepTempExample example = new EmpDepTempExample();
    	EmpDepTempExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdEqualTo(empId);
    	mapper.deleteByExample(example);
    }
}