package com.lionxxw.activiti.variables;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 流程变量 </p>
 *  用于存储在执行流程过程中产生的数据
 *  1.流程变量的生命周期
 *      整个流程实例范围内
 *  2.流程变量的类型
 *      基本类型,实现了对象的序列化类型
 *  3.用什么样的方法可以把流程变量放入到工作流引擎中
 *      在启动流程实例的时候  runtimeservice.startPi
 *      在完成任务的时候     taskService
 *      在流程实例的任何时候  runtimeservice.setVariables
 *  4.用什么样的方法可以把流程变量从工作流引擎中提取出来
 *      根据taskId,根据key可以获取流程变量
 *      根据流程实例id获取流程变量
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/4 上午11:36
 */
public class VariablesTest {

    /**
     * 在启动流程实例的时候,设置流程变量
     * 涉及到的表
     *  1.act_hi_varinst
     *      1.1:说明
     *              表示正在执行的流程实例的流程变量或者已经完成的流程实例的流程变量
     *      1.2:字段
     *              name_:表示流程变量key值
     *              var_type:表示类型
     *  2.act_ru_variable
     *      2.1:说明
     *              表示正在执行的流程实例的流程变量
     *              当流程实例结束以后,流程变量就删除掉了
     *      2.2:字段
     *              和act_hi_varinst表中的字段类似
     */
    @Test
    public void testStartPI(){
        String pid = "qingjia:2:15004";
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("当流程实例启动的时候","设置流程变量");
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().startProcessInstanceById(pid, variables);
    }

    /**
     * 在完成任务的时候,设置流程变量
     */
    @Test
    public void testFinishTask_Variables(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("请假天数", 5);
        variables.put("请假原因", "约会");
        variables.put("备注", "缺钱");
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("17505", variables);
    }

    /**
     * 在流程实例的任何时候设置流程变量
     */
    @Test
    public void testWhenPI(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRuntimeService().setVariable("17501", "在流程实例的任何时候", "在流程实例进行的过程中设置流程变量");
    }

    /**
     * 根据流程实例(runtimeservice)获取流程变量
     */
    @Test
    public void testGetVariables(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        String value = pe.getRuntimeService().getVariable("17501", "在流程实例的任何时候").toString();
        System.out.println(value);
    }

    /**
     * 根据任务(taskservice)获取流程变量
     *      查询的是根据taskId得到的正在执行的流程实例的所有的流程变量
     */
    @Test
    public void testGetVariablesByTaskService(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        String value = pe.getTaskService().getVariable("20005", "在流程实例的任何时候").toString();
        System.out.println(value);
    }

    /**
     * 完成审批
     */
    @Test
    public void testFinishTask(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getTaskService().complete("27502");
    }
}
