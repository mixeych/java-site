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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class MainController {
    @RequestMapping("/")
    public ModelAndView hello(){
        String message = "Hello World!";
        return new ModelAndView("index", "message", message);
    }
    
    
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model){
        model.addAttribute("user", new User());
        return new ModelAndView("login");
    }
    
    @RequestMapping(path = "/check-user", method = RequestMethod.POST)
    public String checkUser(@Valid @ModelAttribute User user, BindingResult result, Model model){

        if (result.hasErrors()) {
            return "login";
        }
        return "redirect:/hello-page";
    }
    
    @RequestMapping(path="/hello-page", method = RequestMethod.GET)
    public String helloPage(User user){
        return "hello";
    }
    
    @RequestMapping(path="/get-json-user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User getJsonUser(@RequestParam (value="name", defaultValue="Dima") String name){
        User user = new User();
        user.setName(name);
        return user;
    }
}
