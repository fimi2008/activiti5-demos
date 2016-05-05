package com.lionxxw.activiti.receive;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * <p>Description: receivetask </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 下午3:10
 */
public class ReceiveTest {
    /**
     * 部署
     */
    @Test
    public void testDeploy() {
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment()
                .addClasspathResource("receive.bpmn").deploy();
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartPi(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById("receive:1:127504");
    }

    /**
     * 没有人参与的节点,使用receivetask
     */
    @Test
    public void testExecutionNext(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().signal("130001"); // signal 信号
    }

    /**
     * 在完成任务的时候
     */
    @Test
    public void testFinishTask(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("120002");
    }
}
