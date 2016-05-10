package com.lionxxw.employee.dao;

import java.util.List;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtil;
import com.lionxxw.common.utils.StringUtil;
import com.lionxxw.employee.dto.EmployeeDto;
import com.lionxxw.employee.entity.EmpDepView;
import com.lionxxw.employee.entity.EmpDepViewExample;
import com.lionxxw.employee.mapper.EmpDepViewMapper;

/**
 * <p>Description: 员工部门关联视图dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 下午9:47
 */
@Repository
public class EmpDepViewDao extends MyBatisBaseDao<EmpDepView> {

    @Autowired
    @Getter
    private EmpDepViewMapper mapper;

    public EmpDepView selectByPrimaryKey(Long id) throws Exception{
        EmployeeDto obj = new EmployeeDto();
        obj.setId(id);
        List<EmpDepView> empDepViews = queryByParam(obj, null);
        if (ObjectUtil.notEmpty(empDepViews)) return empDepViews.get(0);
        return null;
    }

    public List<EmpDepView> queryByParam(EmployeeDto obj, PageQuery query) throws Exception{
        EmpDepViewExample example = new EmpDepViewExample();
        EmpDepViewExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("create_time desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("create_time desc");
        }
        List<EmpDepView> results = mapper.selectByExample(example);
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
    	EmpDepViewExample example = new EmpDepViewExample();
    	EmpDepViewExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(EmployeeDto params, EmpDepViewExample.Criteria criteria) {
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
            if (ObjectUtil.notNull(params.getIsManager())){
                criteria.andIsManagerEqualTo(params.getIsManager());
            }
            if (StringUtil.notTrimEmpty(params.getAccount())){
                criteria.andAccountEqualTo(params.getAccount().trim());
            }
            if (StringUtil.notTrimEmpty(params.getPwd())){
                criteria.andPwdEqualTo(params.getPwd().trim());
            }
            if (ObjectUtil.notNull(params.getDepId())){
                criteria.andDepIdEqualTo(params.getDepId());
            }
            if (ObjectUtil.notNull(params.getParentDepId())){
                criteria.andParentDepIdEqualTo(params.getParentDepId());
            }
        }
        criteria.andStateEqualTo(DataStatus.ENABLED);
    }
}