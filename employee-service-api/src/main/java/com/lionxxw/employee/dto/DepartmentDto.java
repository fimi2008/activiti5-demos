package com.lionxxw.employee.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**		
 * <p>Title: DepartmentDto </p>
 * <p>Description: 类描述:部门数据封装</p>
 * <p>Copyright (c) 2015 </p>
 * @author xiang_wang	
 * @date 2016年5月10日下午1:08:53
 * @version 1.0
 */
public class DepartmentDto implements Serializable{
	
	@Getter
	@Setter
	private Long id;		// 部门id

	@Getter
	@Setter
    private String name;	// 部门名称

	@Getter
	@Setter
    private Long parentDepId;	// 上级部门

	@Getter
	@Setter
    private Date createTime;	// 部门创建时间
	
	@Getter
    @Setter
    private Integer state;		// 数据状态(0-禁用,1-有效)
}