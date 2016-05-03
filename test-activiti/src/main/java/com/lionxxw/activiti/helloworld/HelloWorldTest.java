package com.lionxxw.activiti.helloworld;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * <p>Description: activiti demo </p>
 *
 *  1.部署流程
 *  2.启动流程实例
 *  3.请假人发出请假申请
 *  4.部门经理查看任务
 *  5.部门经理审批
 *  6.最终的boss审批
 * @author wangxiang
 * @version 1.0
 * @time 16/5/3 下午8:27
 */
public class HelloWorldTest {

    /**
     * 部署
     */
    @Test
    public void testDeploy(){
        // 得到流程引擎
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment()
                .addClasspathResource("qingjia.bpmn")
                //.addClasspathResource("qingjia.png")
                .deploy();
    }

    /**
     * 启动流程实例
     */
    @Test
    public void tesStartProcessInstance(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById("qingjia:1:4");
    }

    /**
     * 提交请假申请
     */
    @Test
    public void testQingjia(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("2504"); // 查找ACT_RU_TASK
    }

    /**
     * 部门经理查看审批
     */
    @Test
    public void testQueryTesk(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = pe.getTaskService().createTaskQuery().taskAssignee("项目经理").list();
        for (Task task : tasks){
            System.out.println(task.getId()+":"+task.getName());
        }
    }

    /**
     * 项目经理审批
     */
    @Test
    public void testFinishTask_Manager(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("5002");
    }

    /**
     * 总监查看审批
     */
    @Test
    public void testBossQueryTesk(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = pe.getTaskService().createTaskQuery().taskAssignee("技术总监").list();
        for (Task task : tasks){
            System.out.println(task.getId()+":"+task.getName());
        }
    }

    /**
     * 总监完成审批
     */
    @Test
    public void testFinishTask_Boss(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("7502");
    }
}