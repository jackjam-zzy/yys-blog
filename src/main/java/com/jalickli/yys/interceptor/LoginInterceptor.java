package com.jalickli.yys.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 登录过滤器
* */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /*
    * 预处理
    * */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin");
            return false;
        }
     return true;  //继续往下执行
    }
}
