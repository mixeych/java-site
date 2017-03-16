package com.mixey.springmvc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import com.mixey.springmvc.model.User;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @RequestMapping(path="/upload-file")
    public String uploadFile(@RequestParam("upfile") MultipartFile file, RedirectAttributes redirectAttributes)
    {
//        if (files.length == 0){
//            redirectAttributes.addFlashAttribute("error", "Files is empty");
//            return "redirect:/hello-page";
//        }
            
        String fileName = "";
        boolean empt = file.isEmpty();
        if(empt){
            redirectAttributes.addFlashAttribute("message", "File is empty");
            return "redirect:/hello-page";
        }
        try {
            byte[] bytes = file.getBytes();
            if(bytes.length == 0){
                redirectAttributes.addFlashAttribute("message", "File is empty");
                return "redirect:/hello-page";
            }
            // Creating the directory to store file
            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "tmpFiles");
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + file.getOriginalFilename());
            fileName += serverFile.getName()+" ";
            BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload file");
            return "redirect:/hello-page";
        }
        
        redirectAttributes.addFlashAttribute("message", "You succesful upload file:"+fileName);
        return "redirect:/upload-success";
    }
    
    @GetMapping("/upload-success")
    public String uploadSuccess()
    {
        
        return "uploaded-success";
    }
}
