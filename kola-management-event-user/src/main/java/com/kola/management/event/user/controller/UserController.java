package com.kola.management.event.user.controller;

import com.kola.management.event.user.business.exceptions.UserBusinessException;
import com.kola.management.event.user.business.user.IUserBusiness;
import com.kola.management.event.user.dto.user.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserBusiness userBusiness;

    @GetMapping(value = {"/list","/page","page/{pageNo}"})
    public String login(@PathVariable(value = "pageNo", required = false) Integer pageNo, Model model){
//        model.addAttribute("eventData", userBusiness.getUserListData(pageNo));
        return  "users";
    }
}
