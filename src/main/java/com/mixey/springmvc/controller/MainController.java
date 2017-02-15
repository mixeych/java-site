package com.mixey.springmvc.controller;

import com.mixey.springmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping("/")
    public ModelAndView hello(){
        String message = "Hello World!";
        return new ModelAndView("index", "message", message);
    }
    
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("login", "user", new User());
    }
    
    @RequestMapping(path = "/check-user", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("check-user");
        return modelAndView;
    }
}
