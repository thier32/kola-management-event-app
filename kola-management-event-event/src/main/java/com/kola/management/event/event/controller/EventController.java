package com.kola.management.event.event.controller;

import com.kola.management.event.event.business.IEventBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events/")
public class EventController {

    @Autowired
    IEventBusiness eventBusiness;

    @GetMapping("list")
    public String eventlist(Model model){
//        List<String> snames = this.moduleService.getModuleNames();
//        snames.forEach(System.out::println);
//        List<Parameter> sParameters = this.parameterService.findDistinctParameterByName(snames);
//        model.addAttribute("servicesName",snames);
//        model.addAttribute("services",sParameters);
        model.addAttribute("eventData", eventBusiness.getListData());
        return  "events";
    }
}
