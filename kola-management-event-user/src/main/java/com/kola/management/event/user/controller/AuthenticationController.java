package com.kola.management.event.user.controller;

import com.kola.management.event.user.business.exceptions.UserBusinessException;
import com.kola.management.event.user.business.user.IUserBusiness;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthenticationController {

    @Autowired
    IUserBusiness userBusiness;

    @GetMapping(value = {"login","register","admin","admin/register"})
    public String login(Model model, HttpServletRequest request,RedirectAttributes redirectAttributes){
        model.addAttribute("isRegister",false);
        if (request.getRequestURI().contains("/register")){
            model.addAttribute("isRegister",true);
            if (!model.containsAttribute("userdto")){
                model.addAttribute("userdto",new UserDto());
            }
        }

        if(request.getRequestURI().equalsIgnoreCase("/login")
        || request.getRequestURI().equalsIgnoreCase("/admin")
        ){
            model.addAttribute("userdto",new UserDto());
        }

        if(request.getRequestURI().contains("/admin")){
            model.addAttribute("isAdmin",true);
            model.addAttribute("userdto",new UserDto(true));
        }

        return "login";
    }


    @GetMapping("welcome")
    public String welcome(){
        return "welcome";
    }


    @PostMapping(value = {"register","admin/register"})
    public String userSave(UserDto userDto,HttpServletRequest request, RedirectAttributes redirectAttributes){
        try {
                userBusiness.createUser(userDto);
        }catch (UserBusinessException userServiceException){
            redirectAttributes.addFlashAttribute("message",userServiceException.getMessage());
            redirectAttributes.addFlashAttribute("userdto",userDto);
        }

        if(userDto.isadmin() == null){
            return "redirect:/register";
        }

        return "redirect:/admin/register";
    }

}
