package com.lionxxw.activiti.sequenceflow;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Description: 流程分支 message判断是否重要,需走总经理审批 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 下午1:36
 */
public class SequenceflowTest {

    /**
     * 部署
     */
    @Test
    public void testDeploy() {
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment()
                .addClasspathResource("sequenceflow.bpmn").deploy();
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartPi(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById("sequenceflow:1:75004");
    }

    /**
     * 在完成任务的时候.给下一个节点的流程变量的名称赋值
     */
    @Test
    public void testFinishTask(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("85004");
    }

    /**
     * 部门经理审批
     */
    @Test
    public void testFinishTask_Manager(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("message", "不重要");
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("87502",variables);
    }
}
