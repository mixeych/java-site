/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mixey.springmvc.interceptor;

import com.mixey.springmvc.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author dim
 */
public class AdminIntercepotor extends HandlerInterceptorAdapter {
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User) modelAndView.getModel().get("user");
        if(!user.isAdmin()){
            response.sendRedirect(request.getContextPath());
        }
    }

    
    
}
