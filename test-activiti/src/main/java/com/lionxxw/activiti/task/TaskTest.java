package com.lionxxw.activiti.task;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 将流程assignee 设置为变量 </p>
 *
 * 通过设置流程变量赋值任务的执行人的应用场合
 *      在进入该节点之前,必须给该任务的执行人赋值: 解释:该任务还没有进行,就得给该任务的执行人赋值了
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/4 下午2:54
 */
public class TaskTest {

    /**
     * 部署
     */
    @Test
    public void testDeploy() {
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment()
                .addClasspathResource("task.bpmn").deploy();
    }

    /**
     * 删除部署
     */
    @Test
    public void testDelete(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().deleteDeployment("32501", true);
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartPi(){
        Map<String, Object> variables = new HashMap<String, Object>();
        /**
         * 通过流程变量的形式给
         *  <userTask activiti:assignee="#{applicator}" activiti:async="false" activiti:exclusive="true" id="请假申请" name="请假申请"/>
         * 中的applicator赋值.
         * 必须在进入该userTask节点之前给applicator赋值
         */
        variables.put("applicator","张三丰"); // 申请人
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById("task:1:35004", variables);
    }

    /**
     * 在完成任务的时候.给下一个节点的流程变量的名称赋值
     */
    @Test
    public void testFinishTask(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("boss","常总监");
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("42503", variables);
    }
}