package com.kola.management.event.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {

//    @Autowired
//    IModuleService moduleService;

    @GetMapping("dashboard")
    public String dashboard(Model model){
//        List<String> snames = this.moduleService.getModuleNames();
//        snames.forEach(System.out::println);
//        List<Parameter> sParameters = this.parameterService.findDistinctParameterByName(snames);
//        model.addAttribute("servicesName",snames);
//        model.addAttribute("services",sParameters);
        return  "dashboard";
    }
}
