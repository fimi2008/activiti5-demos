package com.lionxxw.employee.entity;

import java.util.Date;

public class EmpDepTemp {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_dep_temp.id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_dep_temp.dep_id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    private Long depId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_dep_temp.emp_id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    private Long empId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_dep_temp.create_time
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_dep_temp.id
     *
     * @return the value of emp_dep_temp.id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_dep_temp.id
     *
     * @param id the value for emp_dep_temp.id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_dep_temp.dep_id
     *
     * @return the value of emp_dep_temp.dep_id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public Long getDepId() {
        return depId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_dep_temp.dep_id
     *
     * @param depId the value for emp_dep_temp.dep_id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public void setDepId(Long depId) {
        this.depId = depId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_dep_temp.emp_id
     *
     * @return the value of emp_dep_temp.emp_id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_dep_temp.emp_id
     *
     * @param empId the value for emp_dep_temp.emp_id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_dep_temp.create_time
     *
     * @return the value of emp_dep_temp.create_time
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_dep_temp.create_time
     *
     * @param createTime the value for emp_dep_temp.create_time
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}