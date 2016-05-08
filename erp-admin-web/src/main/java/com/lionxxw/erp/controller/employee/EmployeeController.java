package com.lionxxw.erp.controller.employee;

import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.model.Response;
import com.lionxxw.common.utils.ObjectUtil;
import com.lionxxw.employee.dto.EmployeeDto;
import com.lionxxw.employee.service.EmployeeService;
import com.lionxxw.erp.controller.BaseController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(value="/emp")
public class EmployeeController extends BaseController {

    protected static final Log logger = LogFactory.getLog(EmployeeController.class);
    
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "list")
    public ModelAndView findpage(EmployeeDto dto, PageQuery query) throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageResult<EmployeeDto> result = employeeService.queryByPage(dto, query);
        mv.setViewName("/emp/list");
        mv.addObject("datas", result);
        mv.addObject("params", dto);
        return mv;
    }
    
    @RequestMapping(value = "add")
    public ModelAndView add(Long id) throws Exception {
        ModelAndView mv = this.getModelAndView();
        List<EmployeeDto> managers = employeeService.queryByParam(null);
        mv.addObject("managers", managers);
        if (ObjectUtil.notNull(id)){
            EmployeeDto emp = employeeService.getById(id);
            mv.addObject("emp", emp);
        }
        mv.setViewName("/emp/add");
        return mv;
    }
    
    /**		
     * <p>Description: 新增修员工改接口 </p>
     * 
     * @param dto 员工对象
     * @return Response<String>
     * @author wangxiang	
     * @date 16/5/6 下午8:19
     * @version 1.0
     */
    @RequestMapping(value = "save")
    @ResponseBody
    public Response<String> save(EmployeeDto dto) throws Exception{
        Response<String> response = new Response<String>();
        try {
            if (ObjectUtil.notEmpty(dto.getId())){
                employeeService.update(dto);
                response.setMessage("更新成功~");
            }else{
                employeeService.save(dto);
                response.setMessage("新增成功~");
            }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("save方法抛出异常:" + e.getMessage());
            response.setMessage("操作失败");
            response.setStatus(DataStatus.HTTP_FAILE);
		}
        return response;
    }
}