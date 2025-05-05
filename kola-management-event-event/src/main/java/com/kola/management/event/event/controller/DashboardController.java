package com.kola.management.event.event.controller;

import com.kola.management.event.event.business.IEventBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    IEventBusiness eventBusiness;

    @GetMapping(value = {"","/page","/page/{pageNo}"})
    public String dashboard(@PathVariable(value = "pageNo", required = false) Integer pageNo, Model model){
        model.addAttribute("eventData", eventBusiness.getEventListData(pageNo));
        return  "dashboard";
    }
}
