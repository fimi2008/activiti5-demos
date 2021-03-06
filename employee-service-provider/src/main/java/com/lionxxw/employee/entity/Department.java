package com.lionxxw.employee.entity;

import java.util.Date;

public class Department {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.name
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.parent_dep_id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    private Long parentDepId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.create_time
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.state
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    private Integer state;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.id
     *
     * @return the value of department.id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.id
     *
     * @param id the value for department.id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.name
     *
     * @return the value of department.name
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.name
     *
     * @param name the value for department.name
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.parent_dep_id
     *
     * @return the value of department.parent_dep_id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public Long getParentDepId() {
        return parentDepId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.parent_dep_id
     *
     * @param parentDepId the value for department.parent_dep_id
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public void setParentDepId(Long parentDepId) {
        this.parentDepId = parentDepId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.create_time
     *
     * @return the value of department.create_time
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.create_time
     *
     * @param createTime the value for department.create_time
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.state
     *
     * @return the value of department.state
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.state
     *
     * @param state the value for department.state
     *
     * @mbggenerated Tue May 10 12:56:35 CST 2016
     */
    public void setState(Integer state) {
        this.state = state;
    }
}