package com.services.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.services.spring.model.Quote;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import com.services.spring.services.MyJWTService;

@RestController
public class MyRestController {

    @RequestMapping(value = "/")
    public String test() {
        return "End Points \n ";
    }

    @RequestMapping(value ="/rest")
    public String getJWTToken() {
        MyJWTService myservice = new MyJWTService();
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject(myservice.getJWTSource(), Quote.class);
        return quote.toString();
    }


}
