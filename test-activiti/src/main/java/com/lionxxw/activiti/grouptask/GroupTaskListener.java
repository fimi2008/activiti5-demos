package com.lionxxw.activiti.grouptask;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * <p>Description: 用户任务组监听器 </p>
 * 缺点:
 *     1.如果在taskListener中操作了数据库,
 *     这意味着只要进入该节点就得操作数据一次.
 *     2.这个类不能放入spring容器中,所以该类
 *     中的方法不能使用spring的声明式事务处理
 * 优点:
 *      可以在方法中引入servletContext或者ApplicationContext
 * @author wangxiang
 * @version 1.0
 * @time 16/5/5 上午10:39
 */
public class GroupTaskListener implements TaskListener {

    public void notify(DelegateTask delegateTask) {
        delegateTask.addCandidateUser("小A");
        delegateTask.addCandidateUser("小B");
        delegateTask.addCandidateUser("小C");
    }
}
