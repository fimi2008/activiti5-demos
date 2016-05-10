package com.lionxxw.erp.controller.employee;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.model.Response;
import com.lionxxw.common.utils.ObjectUtil;
import com.lionxxw.employee.dto.DepartmentDto;
import com.lionxxw.employee.service.DepartmentService;
import com.lionxxw.erp.controller.BaseController;

/**		
 * <p>Title: DepartmentController </p>
 * <p>Description: 类描述:部门操作控制层</p>
 * <p>Copyright (c) 2015 </p>
 * @author xiang_wang	
 * @date 2016年5月10日下午1:42:47
 * @version 1.0
 */
@Controller
@RequestMapping(value="/dep")
public class DepartmentController extends BaseController {

    protected static final Log logger = LogFactory.getLog(DepartmentController.class);
    
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "list")
    public ModelAndView findpage(DepartmentDto dto, PageQuery query) throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageResult<DepartmentDto> result = departmentService.queryByPage(dto, query);
        mv.setViewName("/dep/list");
        mv.addObject("datas", result);
        mv.addObject("params", dto);
        return mv;
    }
    
    /**
     * 跳转新增/修改部门信息页面
     * @param id
     * @return
     * @throws Exception
     * @author xiang_wang
     * 2016年5月10日下午1:45:45
     */
    @RequestMapping(value = "add")
    public ModelAndView add(Long id) throws Exception {
        ModelAndView mv = this.getModelAndView();
        List<DepartmentDto> deps = departmentService.queryByParam(null);
        if (ObjectUtil.notNull(id)){
            DepartmentDto dep = departmentService.getById(id);
            mv.addObject("dep", dep);
            deps.remove(dep); // 删除本身
        }
        mv.addObject("deps", deps);
        mv.setViewName("/dep/add");
        return mv;
    }
    
    /**		
     * <p>Description: 新增修改部门接口 </p>
     * 
     * @param dto 部门对象
     * @return Response<String>
     * @author wangxiang	
     * @date 16/5/6 下午8:19
     * @version 1.0
     */
    @RequestMapping(value = "save")
    @ResponseBody
    public Response<String> save(DepartmentDto dto) throws Exception{
        Response<String> response = new Response<String>();
        try {
            if (ObjectUtil.notEmpty(dto.getId())){
            	departmentService.update(dto);
                response.setMessage("更新成功~");
            }else{
            	departmentService.save(dto);
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