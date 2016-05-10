package com.lionxxw.erp.controller.activiti;

import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.model.Response;
import com.lionxxw.common.utils.ObjectUtil;
import com.lionxxw.common.utils.StringUtil;
import com.lionxxw.employee.dto.EmployeeDto;
import com.lionxxw.employee.service.EmployeeService;
import com.lionxxw.erp.controller.BaseController;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/act")
public class ActivitiController extends BaseController {

    protected static final Log logger = LogFactory.getLog(ActivitiController.class);
    
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private HistoryService historyService;

    /**
     * 查询所有已发布的流程
     * @return ModelAndView
     */
    @RequestMapping("processList")
    public ModelAndView processList(){
        ModelAndView mv = getModelAndView();
        /**
         * 倒序查出所有已发布的流程
         */
        List<ProcessDefinition> list =  repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();

        // 过滤重复发布的流程 取最新的流程
        Map<String, Object> map = new HashMap<String, Object>();
        if(list != null && list.size() > 0){
            for(ProcessDefinition pd : list){
                map.put(pd.getKey(), pd);
            }
        }

        // 返回过滤之后的流程
        mv.addObject("datas", map.values());
        mv.setViewName("/act/processList");
        return mv;
    }

    /**
     * 获取流程图
     * @param response
     * @param pdId 流程id
     */
    @RequestMapping("getImage")
    public void getImage(HttpServletResponse response, String pdId) {
        InputStream inputStream = repositoryService.getProcessDiagram(pdId);
        try {
            OutputStream out = response.getOutputStream();
            // 把图片的输入流程写入resp的输出流中
            byte[] b = new byte[1024];
            for (int len = -1; (len = inputStream.read(b)) != -1;) {
                out.write(b, 0, len);
            }
            // 关闭流
            out.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 当前流程图
     * @param taskId
     */
    public Map<String, Object> currentImage(String taskId) {
        // 1. 获取到当前活动的ID
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        String currentActivitiId = pi.getActivityId();
        // 2. 获取到流程定义
        ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        // 3. 使用流程定义通过currentActivitiId得到活动对象
        ActivityImpl activity = pd.findActivity(currentActivitiId);
        // 4. 获取活动的坐标
        Map<String, Object> coordinates = new HashMap<String, Object>();
        coordinates.put("x", activity.getX());
        coordinates.put("y", activity.getY());
        coordinates.put("width", activity.getWidth());
        coordinates.put("height", activity.getHeight());

        return coordinates;
    }

    /**
     * 流程申请 (根据流程定义id,中的key,跳转不同的页面)
     * @param pdId 流程id
     * @return ModelAndView
     */
    @RequestMapping("apply")
    public ModelAndView applyLeave(String pdId){
        if (StringUtil.isNull(pdId)){
            throw new RuntimeException("对不起,参数异常!");
        }
        String[] split = pdId.split(":");
        ModelAndView mv = getModelAndView();
        mv.addObject("pdId", pdId);
        mv.setViewName("/act/"+split[0]);
        return mv;
    }

    /**
     * 启动请假流程
     * @param leaveDay 请假天数
     * @param leaveReason 请假原因
     * @param pdId 请假流程id pdId 由pdkey:pdversion:随机数 组成
     * @return ModelAndView
     */
    @RequestMapping("leave")
    @ResponseBody
    public Response<String> leave(int leaveDay, String leaveReason, String pdId) throws Exception{
        Response<String> response = new Response<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        /**
         * 办理人必须是字符串类型的
         */
        EmployeeDto emp = getSessionEmp();
        params.put("applicator", emp.getId());

        /**
         * 启动一个请假流程的实例
         */
        // 发起流程是指定发起人
        identityService.setAuthenticatedUserId(String.valueOf(emp.getId()));
        ProcessInstance pi =  runtimeService.startProcessInstanceById(pdId, params);

        /**
         * 根据实例ID查询任务
         * 如果启动多个流程实例的时候要完成多个任务
         */
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        params.clear();
        // 查找该员工所在部门经理
        if (emp.getIsManager()){
            params.put("manager", emp.getId());
        }else{
            EmployeeDto param = new EmployeeDto();
            param.setDepId(emp.getDepId());
            param.setIsManager(true);
            List<EmployeeDto> managers = employeeService.queryByParam(param);
            if (ObjectUtil.notEmpty(managers)){
                params.put("manager", managers.get(0).getId());
            }else{
                param.setDepId(emp.getParentDepId());
                managers = employeeService.queryByParam(param);
                if (ObjectUtil.notEmpty(managers)){
                    params.put("manager", managers.get(0).getId());
                }else{
                    throw new RuntimeException("对不起,你所在部门及上一级部门,均无经理可以进行审批!");
                }
            }
        }
        params.put("leaveDay", leaveDay);
        params.put("leaveReason", leaveReason);
        taskService.complete(task.getId(), params);
        response.setMessage("请假申请成功!");
        return response;
    }

    /**
     * 查询我的任务
     * @return ModelAndView
     */
    @RequestMapping("queryMyTask")
    public ModelAndView queryMyTask(){
        ModelAndView mv = getModelAndView();
        EmployeeDto emp = getSessionEmp();
        List<Task> list = taskService.createTaskQuery().taskAssignee(String.valueOf(emp.getId())).orderByTaskCreateTime().desc().list();
        mv.addObject("datas", list);
        mv.setViewName("/act/myTask");
        return mv;
    }

    /**
     * 查询所在角色任务
     * @param request
     * @return
     */
    /*@RequestMapping("queryRoleTask")
    public ModelAndView queryRoleTask(HttpServletRequest request){

        AuthRole role = (AuthRole)request.getSession().getAttribute("role");

        List<Task> list = taskService.createTaskQuery().taskCandidateGroup(String.valueOf(role.getId())).orderByTaskCreateTime().desc().list();

		*//*for(Task task : list){
			// 获取的到参数
			Map<String, Object> params = runtimeService.getVariables(task.getExecutionId());
			// 获取的到参数
			Map<String, Object> params =  taskService.getVariables(task.getId());
			Map<String, Object> variables = task.getProcessVariables(); 获取不到参数
		}*//*

        return new ModelAndView("groupTask", "list", list);
    }*/

    /**
     * 查看任务详情
     * @param taskId
     * @return
     */
    @RequestMapping("taskDeatil")
    public ModelAndView taskDeatil(String taskId) throws Exception{
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        /**
         * 获取流程定义id
         */
        ProcessDefinition pd = repositoryService.getProcessDefinition(task.getProcessDefinitionId());

        Map<String, Object> params =  taskService.getVariables(taskId);
        String pdId = task.getProcessDefinitionId();
        params.put("emp", employeeService.getById(Long.valueOf(params.get("applicator")+"")));
        params.put("taskId", taskId);
        params.put("pdId", pdId);
        params.put("coordinates", currentImage(taskId));
        params.put("assignee", getSessionEmp().getId());
        List<HistoricTaskInstance> instans = historyService.createHistoricTaskInstanceQuery().
                processInstanceId(task.getProcessInstanceId()).orderByHistoricTaskInstanceStartTime().asc().list();
        params.put("instans", instans);

        ModelAndView mv = getModelAndView();
        mv.setViewName("/act/"+pdId.split(":")[0]+"Detail");
        mv.addObject("datas", params);
        return mv;
    }

    /**
     * 认领任务
     * @param taskId 任务ID
     * @return
     */
    @RequestMapping("claimTask")
    public Response<String> claimTask(String taskId){
        EmployeeDto emp = getSessionEmp();
        taskService.claim(taskId, String.valueOf(emp.getId()));
        Response<String> response = new Response<String>();
        response.setMessage("任务认领成功!");
        return response;
    }

    /**
     * 完成任务
     * @param taskId
     * @param isAgree 1-同意,0-拒绝
     * @return
     */
    @RequestMapping("completeTask")
    @ResponseBody
    public Response<String> completeTask(String taskId, Integer isAgree) throws Exception{
        if(isAgree != null){
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("isAgree", isAgree);
            EmployeeDto emp = getSessionEmp();
            EmployeeDto param = new EmployeeDto();
            param.setDepId(emp.getParentDepId());
            param.setIsManager(true);
            List<EmployeeDto> managers = employeeService.queryByParam(param);
            if (ObjectUtil.notEmpty(managers)){
                params.put("boss", managers.get(0).getId());
            }else{
                throw new RuntimeException("对不起,你的上一级部门,无经理可以进行审批!");
            }

            taskService.complete(taskId, params);
        }else{
            taskService.complete(taskId);
        }

        Response<String> response = new Response<String>();
        response.setMessage("成功!");
        return response;
    }

    /**
     * 运行的进程不能删除
     * @param taskId
     * @return
     */
    @RequestMapping("deleteTask")
    @ResponseBody
    public Response<String> deleteTask(String taskId){

        //taskService.deleteTask(taskId, true);

        taskService.deleteTask(taskId);

        Response<String> response = new Response<String>();
        response.setMessage("成功!");
        return response;
    }

    /**
     * 删除部署
     * @param depId
     * @return
     */
    @RequestMapping(value = "deleteDeployment")
    @ResponseBody
    public Response<String> deleteDeployment(String depId){
        Response<String> response = new Response<String>();
        try {
            repositoryService.deleteDeployment(depId);
            response.setMessage("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("删除失败!");
            response.setStatus(DataStatus.HTTP_FAILE);
        }
        return response;
    }
}