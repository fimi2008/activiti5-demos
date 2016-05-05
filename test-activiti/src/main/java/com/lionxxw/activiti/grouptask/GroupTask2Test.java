package com.lionxxw.activiti.grouptask;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 流程组员审核 设置流程变量userIds</p>
 *  <userTask activiti:candidateUsers="#{userIds}" activiti:exclusive="true" id="usertask2" name="usertask2"/>
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 上午9:42
 */
public class GroupTask2Test {

    /**
     * 部署
     */
    @Test
    public void testDeploy() {
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment()
                .addClasspathResource("grouptask2.bpmn").deploy();
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartPi(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userIds","王一,王二,王三,王四");
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById("grouptask2:1:60004", variables);
    }

    /**
     * 根据任务的候选人查看组任务
     */
    @Test
    public void testQueryGroupTaskByCandidate(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = pe.getTaskService().createTaskQuery().taskCandidateUser("王一").list();
        for (Task task : tasks){
            System.out.println(task.getName());
        }
    }

    /**
     * 根据组任务查看候选人
     */
    @Test
    public void testQueryCandidateByGroupTask(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        List<IdentityLink> identityLinks = pe.getTaskService().getIdentityLinksForTask("62505");
        for (IdentityLink identityLink : identityLinks){
            System.out.println(identityLink.getUserId());
        }
    }

    /**
     * 领取任务
     * 选取任何一个人接受该任务
     */
    @Test
    public void testClaimTask(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().claim("62505","王二");
    }
}
