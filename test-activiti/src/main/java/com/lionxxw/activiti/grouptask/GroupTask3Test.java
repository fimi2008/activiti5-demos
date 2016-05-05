package com.lionxxw.activiti.grouptask;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * <p>Description: 流程组员审核 设置taskListener</p>
 * <activiti:taskListener class="com.lionxxw.activiti.grouptask.GroupTaskListener" event="create"/>
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 上午9:42
 */
public class GroupTask3Test {

    /**
     * 部署
     */
    @Test
    public void testDeploy() {
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment()
                .addClasspathResource("grouptask3.bpmn").deploy();
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartPi(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById("grouptask3:1:65004");
    }

    /**
     * 根据任务的候选人查看组任务
     */
    @Test
    public void testQueryGroupTaskByCandidate(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = pe.getTaskService().createTaskQuery().taskCandidateUser("小A").list();
        for (Task task : tasks){
            System.out.println(task.getId() + ":" + task.getName());
        }
    }

    /**
     * 根据组任务查看候选人
     */
    @Test
    public void testQueryCandidateByGroupTask(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        List<IdentityLink> identityLinks = pe.getTaskService().getIdentityLinksForTask("67504");
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
        pe.getTaskService().claim("67504","小A");
    }
}
