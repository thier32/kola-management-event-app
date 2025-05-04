package com.kola.management.event.event.controller;

import com.kola.management.event.event.business.IEventBusiness;
import com.kola.management.event.event.business.IEventSpotBusiness;
import com.kola.management.event.event.business.exceptions.EventBusinessException;
import com.kola.management.event.event.business.exceptions.EventSpotBusinessException;
import com.kola.management.event.event.dto.event.EventDto;
import com.kola.management.event.event.dto.eventspot.EventSpotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/eventspot/")
public class EventSpotController {

    @Autowired
    IEventSpotBusiness eventSpotBusiness;

    @Autowired
    IEventBusiness eventBusiness;

    @GetMapping(value = {"list","page","page/{pageNo}"})
    public String eventStoplist(@PathVariable(value = "pageNo", required = false) Integer pageNo, Model model){
        model.addAttribute("eventSpotData", eventSpotBusiness.getEventSpotListData(pageNo));
        return  "event_spots";
    }

    @GetMapping("add")
    public String eventStopAdd(Model model){

        model.addAttribute("eventSpotDto", new EventSpotDto(eventBusiness.getAllEventListData()));
        return "event_spot_form";
    }

    @PostMapping("save")
    public String eventStopSave(EventSpotDto eventSpotDto,
                            RedirectAttributes redirectAttributes){
        try {
            eventSpotBusiness.createEventSpot(eventSpotDto);
        } catch (EventSpotBusinessException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/eventspot/list";
    }

    @GetMapping("edit/{eventSpotId}")
    public String eventSpotEdit(@PathVariable("eventSpotId") Long eventSpotId, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("eventSpotDto", eventSpotBusiness.getEventSpot(eventSpotId));
            return "event_spot_form";
        } catch (Exception|EventSpotBusinessException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/eventspot/list";
        }
    }

//    @GetMapping("publish/{eventId}")
//    public String eventPublish(@PathVariable("eventId") Long eventId, Model model, RedirectAttributes redirectAttributes){
//        try {
//            model.addAttribute("eventDto", eventBusiness.publishEvent(eventId));
//        } catch (Exception|EventBusinessException e) {
//            redirectAttributes.addFlashAttribute("message", e.getMessage());
//        }
//        return "redirect:/events/list";
//    }

}
