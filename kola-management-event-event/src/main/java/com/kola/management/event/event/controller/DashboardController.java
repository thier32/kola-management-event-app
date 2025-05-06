package com.kola.management.event.event.controller;

import com.kola.management.event.event.business.IEventBusiness;
import com.kola.management.event.event.dto.event.EventDto;
import com.kola.management.event.event.dto.event.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/")
public class DashboardController {
    @Autowired
    IEventBusiness eventBusiness;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("searchDto",new SearchDto());
        return  "index";
    }

    @PostMapping("search")
    public String searchIndex(SearchDto searchDto, Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("searchDto",searchDto);
        return  "index";
    }



    @GetMapping(value = {"dashboard","dashboard/page","dashboard/page/{pageNo}"})
    public String dashboard(@PathVariable(value = "pageNo", required = false) Integer pageNo, Model model){
        model.addAttribute("eventData", eventBusiness.getEventListData(pageNo));
        return  "dashboard";
    }
}
