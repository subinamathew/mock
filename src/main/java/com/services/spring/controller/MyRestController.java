package com.services.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @RequestMapping(value = "/tt")
    public String test() {
        return "jhjj";
    }

}
