package com.lionxxw.activiti.grouptask;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * <p>Description: 流程组员审核 </p>
 *  当启动完流程实例以后,流程进入了
 *  <userTask activiti:candidateUsers="张甲,张乙,张丙,张丁" activiti:exclusive="true" id="usertask" name="usertask"/>
 *  该节点在act_ru_identitylink表中插入了4行candidate数据+4行participant数据
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 上午9:42
 */
public class GroupTaskTest {

    /**
     * 部署
     */
    @Test
    public void testDeploy() {
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment()
                .addClasspathResource("grouptask.bpmn").deploy();
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartPi(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById("grouptask:1:55004");
    }

    /**
     * 根据任务的候选人查看组任务
     */
    @Test
    public void testQueryGroupTaskByCandidate(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = pe.getTaskService().createTaskQuery().taskCandidateUser("张甲").list();
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
        List<IdentityLink> identityLinks = pe.getTaskService().getIdentityLinksForTask("57504");
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
        pe.getTaskService().claim("57504","张乙");
    }
}
