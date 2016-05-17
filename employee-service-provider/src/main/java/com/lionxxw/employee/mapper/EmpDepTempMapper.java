package com.lionxxw.employee.mapper;

import com.lionxxw.employee.entity.EmpDepTemp;
import com.lionxxw.employee.entity.EmpDepTempExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpDepTempMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    int countByExample(EmpDepTempExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    int deleteByExample(EmpDepTempExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    int insert(EmpDepTemp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    int insertSelective(EmpDepTemp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    List<EmpDepTemp> selectByExample(EmpDepTempExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    EmpDepTemp selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    int updateByExampleSelective(@Param("record") EmpDepTemp record, @Param("example") EmpDepTempExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    int updateByExample(@Param("record") EmpDepTemp record, @Param("example") EmpDepTempExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    int updateByPrimaryKeySelective(EmpDepTemp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_dep_temp
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    int updateByPrimaryKey(EmpDepTemp record);
}