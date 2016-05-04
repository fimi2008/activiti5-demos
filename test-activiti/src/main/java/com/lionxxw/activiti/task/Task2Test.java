package com.lionxxw.activiti.task;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 测试使用 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/4 下午5:27
 */
public class Task2Test {
    /**
     * 部署
     */
    @Test
    public void testDeploy() {
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment()
                .addClasspathResource("task2.bpmn").deploy();
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
        variables.put("applicator","张小儿"); // 申请人
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById("task2:1:47504", variables);
    }

    /**
     * 在完成任务的时候.给下一个节点的流程变量的名称赋值
     */
    @Test
    public void testFinishTask(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("50005");
    }
}
