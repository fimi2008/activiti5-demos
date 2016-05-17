package com.lionxxw.erp.controller;

import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.model.Response;
import com.lionxxw.common.utils.ObjectUtil;
import com.lionxxw.employee.dto.EmployeeDto;
import com.lionxxw.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>Description: 登录 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/6 下午1:53
 */
@Controller
@RequestMapping(value = "")
public class LoginController extends BaseController{

    @Autowired
    private EmployeeService employeeService;

    /**		
     * <p>Description: 登录页面 </p>
     * 
     * @return ModelAndView
     * @author wangxiang	
     * @date 16/5/6 下午2:25
     * @version 1.0
     */
    @RequestMapping(value = "toLogin")
    public ModelAndView toLogin(){
        ModelAndView mv = getModelAndView();
        mv.setViewName("login");
        return mv;
    }

    /**		
     * <p>Description: 登录操作 </p>
     * 
     * @param session
     * @param employeeDto
     * @return Response<String>
     * @author wangxiang	
     * @date 16/5/6 下午2:25
     * @version 1.0
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public Response<String> login(HttpSession session, EmployeeDto employeeDto) throws Exception{
        Response<String> response = new Response<String>();
        List<EmployeeDto> employeeDtos = employeeService.queryByParam(employeeDto);
        if (ObjectUtil.isEmpty(employeeDtos)){
            response.setStatus(DataStatus.HTTP_FAILE);
            response.setMessage("用户/密码错误!");
            return response;
        }else{
            session.setAttribute(DataStatus.SESSION_EMP, employeeDtos.get(0));
            return response;
        }
    }

    /**		
     * <p>Description: 后台管理系统主页面 </p>
     * 
     * @param session
     * @return ModelAndView
     * @author wangxiang	
     * @date 16/5/6 下午2:32
     * @version 1.0
     */
    @RequestMapping(value = "main")
    public ModelAndView main(HttpSession session){
        EmployeeDto emp = (EmployeeDto) session.getAttribute(DataStatus.SESSION_EMP);
        ModelAndView mv = getModelAndView();
        if (null == emp){
            mv.setViewName("login");
        }else{
            mv.setViewName("main");
        }
        return mv;
    }

    /**
     * <p>Description: 登出操作 </p>
     *
     * @param session
     * @return ModelAndView
     * @author wangxiang
     * @date 16/5/17 上午9:48
     * @version 1.0
     */
    @RequestMapping(value = "logout")
    public ModelAndView logout(HttpSession session) throws Exception{
        ModelAndView mv = getModelAndView();
        session.removeAttribute(DataStatus.SESSION_EMP);
        mv.setViewName("login");
        return mv;
    }
}
