package com.lionxxw.erp.interceptors;

import com.lionxxw.common.constants.DataStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Description: 后台员工登录拦截器 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/16 下午5:21
 */
public class LoginInterceptor implements HandlerInterceptor {
    private final static List<String> ulrs = Arrays.asList("/login.do","/toLogin.do");

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if (ulrs.contains(path)){
            return true;
        }

        Object emp = request.getSession().getAttribute(DataStatus.SESSION_EMP);
        if (null == emp){
            request.setAttribute("message", "请先登录!");
            request.getRequestDispatcher("/toLogin.do").forward(request, response);
            return false;
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
