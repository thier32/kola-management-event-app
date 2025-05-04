package com.kola.management.event.event.controller;

import com.kola.management.event.event.business.IEventBusiness;
import com.kola.management.event.event.business.exceptions.EventBusinessException;
import com.kola.management.event.event.dto.event.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/events/")
public class EventController {

    @Autowired
    IEventBusiness eventBusiness;

    @GetMapping(value = {"list","page","page/{pageNo}"})
    public String eventlist(@PathVariable(value = "pageNo", required = false) Integer pageNo, Model model){
        model.addAttribute("eventData", eventBusiness.getEventListData(pageNo));
        return  "events";
    }

//    @GetMapping(value = {"page","page/{pageNo}"})
//    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

    @GetMapping("add")
    public String eventAdd(Model model){
        model.addAttribute("eventDto", new EventDto());
        return "event_form";
    }

    @PostMapping("save")
    public String eventSave(EventDto eventDto,
                            RedirectAttributes redirectAttributes){
        try {
            eventBusiness.createEvent(eventDto);
        } catch (EventBusinessException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/events/list";
    }

    @GetMapping("edit/{eventId}")
    public String eventEdit(@PathVariable("eventId") Long eventId, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("eventDto", eventBusiness.getEvent(eventId));
            return "event_form";
        } catch (Exception|EventBusinessException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/events/list";
        }
    }

    @GetMapping("publish/{eventId}")
    public String eventPublish(@PathVariable("eventId") Long eventId, Model model, RedirectAttributes redirectAttributes){
        try {
            model.addAttribute("eventDto", eventBusiness.publishEvent(eventId));
        } catch (Exception|EventBusinessException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/events/list";
    }

}
