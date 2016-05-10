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
import com.lionxxw.employee.dto.DepartmentDto;
import com.lionxxw.employee.entity.Department;
import com.lionxxw.employee.entity.DepartmentExample;
import com.lionxxw.employee.mapper.DepartmentMapper;

/**
 * <p>
 * Title: DepartmentDao
 * </p>
 * <p>
 * Description: 类描述:部门dao实现
 * </p>
 * <p>
 * Copyright (c) 2015
 * </p>
 * 
 * @author xiang_wang
 * @date 2016年5月10日下午1:03:46
 * @version 1.0
 */
@Repository
public class DepartmentDao extends MyBatisBaseDao<Department> {

	@Autowired
	@Getter
	private DepartmentMapper mapper;

	public List<Department> queryByParam(DepartmentDto obj, PageQuery query)
			throws Exception {
		DepartmentExample example = new DepartmentExample();
		DepartmentExample.Criteria criteria = example.createCriteria();
		assemblyParams(obj, criteria);
		if (null != query) {
			example.setOrderByClause("create_time desc limit "
					+ query.getStartNum() + "," + query.getPageSize());
		} else {
			example.setOrderByClause("create_time desc");
		}
		List<Department> results = mapper.selectByExample(example);
		return results;
	}

	/**
	 * <p>
	 * Description: 根据参数统计数据数目
	 * </p>
	 *
	 * @param obj
	 *            查询参数
	 * @return total
	 * @author wangxiang
	 * @date 16/5/6 上午10:25
	 * @version 1.0
	 */
	public int countByParam(DepartmentDto obj) throws Exception {
		DepartmentExample example = new DepartmentExample();
		DepartmentExample.Criteria criteria = example.createCriteria();
		assemblyParams(obj, criteria);
		return mapper.countByExample(example);
	}

	private void assemblyParams(DepartmentDto params,
			DepartmentExample.Criteria criteria) {
		if (null != params) {
			if (ObjectUtil.notNull(params.getId())) {
				criteria.andIdEqualTo(params.getId());
			}
			if (StringUtil.notTrimEmpty(params.getName())) {
				criteria.andNameEqualTo(params.getName().trim());
			}
			if (ObjectUtil.notNull(params.getParentDepId())){
				criteria.andParentDepIdEqualTo(params.getParentDepId());
			}
		}
		criteria.andStateEqualTo(DataStatus.ENABLED);
	}
}
