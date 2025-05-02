package com.kola.management.event.user.controller;

import com.kola.management.event.user.dto.user.UserDto;
import com.kola.management.event.user.services.IUserService;
import com.kola.management.event.user.services.exceptions.UserServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthenticationController {

//    @Autowired
//    ParameterService parameterService;

    @GetMapping(value = {"login","register"})
    public String login(Model model, HttpServletRequest request,RedirectAttributes redirectAttributes){
        model.addAttribute("isRegister",false);
        if (request.getRequestURI().equalsIgnoreCase("/register")){
            model.addAttribute("isRegister",true);
            model.addAttribute("userdto",new UserDto());
        }
        return "login";
    }


    @GetMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    @Autowired
    IUserService userService;

    @PostMapping("register")
    public String userSave(UserDto userDto, RedirectAttributes redirectAttributes){

        try {
            userService.createUser(userDto);
        }catch (UserServiceException userServiceException){
            redirectAttributes.addFlashAttribute("message",userServiceException.getMessage());
        }

        return "ruedirect:/register";
    }

}
