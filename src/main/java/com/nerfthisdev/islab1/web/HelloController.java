package com.nerfthisdev.islab1.web;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)

    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC");
        return "hello";
    }
}
