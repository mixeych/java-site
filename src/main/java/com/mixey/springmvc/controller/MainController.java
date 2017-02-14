package com.mixey.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping("/")
    public ModelAndView hello(){
        String message = "Hello World!";
        return new ModelAndView("index", "message", message);
    }
    
    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
}