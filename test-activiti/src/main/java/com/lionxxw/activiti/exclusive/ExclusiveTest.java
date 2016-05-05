package com.lionxxw.activiti.exclusive;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 排他网关 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 下午2:13
 */
public class ExclusiveTest {

    /**
     * 部署
     */
    @Test
    public void testDeploy() {
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment()
                .addClasspathResource("exclusive.bpmn").deploy();
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartPi(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById("exclusive:1:100004");
    }

    /**
     * 在完成任务的时候.赋值money.通过排他网关进入下一流程
     */
    @Test
    public void testFinishTask(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("money", 600);
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("102504",variables);
    }
}
