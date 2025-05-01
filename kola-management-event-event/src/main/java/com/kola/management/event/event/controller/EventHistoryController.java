package com.kola.management.event.event.controller;

import com.kola.management.event.event.business.IEventBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events/history/")
public class EventHistoryController {

    @Autowired
    IEventBusiness eventBusiness;

    @GetMapping(value = {"list","page","page/{pageNo}"})
    public String eventHistorylist(@PathVariable(value = "pageNo", required = false) Integer pageNo, Model model){
        model.addAttribute("eventHistoryData", eventBusiness.getEventHistoryListData(pageNo));
        return  "events_history";
    }
}
