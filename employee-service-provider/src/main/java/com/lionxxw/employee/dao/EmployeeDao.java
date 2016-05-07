package com.lionxxw.employee.dao;

import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtil;
import com.lionxxw.common.utils.StringUtil;
import com.lionxxw.employee.dto.EmployeeDto;
import com.lionxxw.employee.entity.Employee;
import com.lionxxw.employee.entity.EmployeeExample;
import com.lionxxw.employee.mapper.EmployeeMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 员工dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 下午9:47
 */
@Repository
public class EmployeeDao extends MyBatisBaseDao<Employee> {

    @Autowired
    @Getter
    private EmployeeMapper mapper;

    public List<Employee> queryByParam(EmployeeDto obj, PageQuery query) throws Exception{
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("create_time desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("create_time desc");
        }
        List<Employee> results = mapper.selectByExample(example);
        return results;
    }

    /**
     * <p>Description: 根据参数统计数据数目 </p>
     *
     * @param obj 查询参数
     * @return total
     * @author wangxiang
     * @date 16/5/6 上午10:25
     * @version 1.0
     */
    public int countByParam(EmployeeDto obj) throws Exception{
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(EmployeeDto params, EmployeeExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtil.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (StringUtil.notTrimEmpty(params.getName())){
                criteria.andNameEqualTo(params.getName().trim());
            }
            if (ObjectUtil.notNull(params.getSex())){
                criteria.andSexEqualTo(params.getSex());
            }
            if (StringUtil.notTrimEmpty(params.getMobile())){
                criteria.andMobileEqualTo(params.getMobile().trim());
            }
            if (StringUtil.notTrimEmpty(params.getEmail())){
                criteria.andEmailEqualTo(params.getEmail().trim());
            }
            if (ObjectUtil.notNull(params.getManagerId())){
                criteria.andManagerIdEqualTo(params.getManagerId());
            }
            if (StringUtil.notTrimEmpty(params.getAccount())){
                criteria.andAccountEqualTo(params.getAccount().trim());
            }
            if (StringUtil.notTrimEmpty(params.getPwd())){
                criteria.andPwdEqualTo(params.getPwd().trim());
            }
        }
        criteria.andStateEqualTo(DataStatus.ENABLED);
    }
}