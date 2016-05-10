package com.lionxxw.employee.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lionxxw.common.constants.SexEnum;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: 员工数据封装 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 下午8:17
 */
@JsonIgnoreProperties({"pwd","state"}) // ajax不提供该字段信息
public class EmployeeDto implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String account;

    @Getter
    @Setter
    private String pwd;

    @Getter
    private Integer sex;

    public void setSex(Integer sex) {
        this.sex = sex;
        setSexStr(SexEnum.getSexByIndex(sex));
    }

    @Getter
    @Setter
    private String mobile;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private Boolean isManager;

    @Getter
    @Setter
    private Date createTime;

    @Getter
    @Setter
    private Integer state;

    @Setter
    @Getter
    private String sexStr;
    
    /** 通过视图获取一下字段  **/
    @Setter
    @Getter
    private Long depId;
    
    @Setter
    @Getter
    private String depName;
    
    @Setter
    @Getter
    private Long parentDepId;
}