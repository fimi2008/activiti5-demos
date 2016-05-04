package com.lionxxw.activiti.task;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * <p>Description: 自定义一个任务监听器 </p>
 *
 * <userTask activiti:async="false" activiti:exclusive="true" id="部门经理审批" name="部门经理审批">
 *      <extensionElements>
 *          <activiti:taskListener class="com.lionxxw.activiti.task.MyTaskListener" event="create"/>
 *      </extensionElements>
 * </userTask>
 *  从上述的配置可以看出,一个TaskListener类必须配置到一个userTask节点中
 *  所以当进入到该节点的时候,就触发了MyTaskListener的notify方法
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/4 下午5:19
 */
public class MyTaskListener implements TaskListener {

    public void notify(DelegateTask delegateTask) {
        String assignee = "张三"; // 任务的执行人
        delegateTask.setAssignee(assignee);
    }
}
