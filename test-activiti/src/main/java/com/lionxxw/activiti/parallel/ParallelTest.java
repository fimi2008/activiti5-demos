package com.lionxxw.activiti.parallel;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * <p>Description: 并行网关 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 下午2:38
 */
public class ParallelTest {
    /**
     * 部署
     */
    @Test
    public void testDeploy() {
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment()
                .addClasspathResource("parallel.bpmn").deploy();
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartPi(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById("parallel:1:110004");
    }

    /**
     * 根据piid过滤任务
     */
    @Test
    public void testQueryTaskByPIID(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = pe.getTaskService().createTaskQuery().processInstanceId("112501").list();
        for (Task task : tasks){
            System.out.println(task.getId() + ":" + task.getName());
        }
    }

    /**
     * 根据executionId过滤任务
     */
    @Test
    public void testQueryTaskByExecutionId(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        Task task = pe.getTaskService().createTaskQuery().executionId("112505").singleResult();
        System.out.println(task.getId() + ":" + task.getName());
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
