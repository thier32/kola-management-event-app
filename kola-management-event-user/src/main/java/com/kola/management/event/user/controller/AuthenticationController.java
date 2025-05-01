package com.kola.management.event.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

//    @Autowired
//    ParameterService parameterService;

    @GetMapping("login")
    public String login(){
        //Authentication ath = new UsernamePasswordAuthenticationToken(loginRequest,loginRequest.password());
        //Authentication authentication = this.authenticationManager.authenticate(ath);
        /*Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);*/
        return "login";
    }


    @GetMapping("welcome")
    public String welcome(){
        return "welcome";
    }


//    @GetMapping("dashboard")
//    public String dashboard(){
////        List<String> snames = this.parameterService.findDistinctName();
////        List<Parameter> sParameters = this.parameterService.findDistinctParameterByName(snames);
////        model.addAttribute("servicesName",snames);
////        model.addAttribute("services",sParameters);
//        return  "dashboard";
//    }

}
