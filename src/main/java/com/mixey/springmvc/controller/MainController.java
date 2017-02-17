package com.mixey.springmvc.controller;

import com.mixey.springmvc.model.User;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String checkUser(@Valid @ModelAttribute User user, BindingResult result, Model model){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("check-user");
        if (result.hasErrors()) {
            return "login";
        }
        model.addAttribute("user", user);
        return "check-user";
    }
    
    @RequestMapping(path="/get-json-user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User getJsonUser(@RequestParam (value="name", defaultValue="Dima") String name){
        User user = new User();
        user.setName(name);
        return user;
    }
}
