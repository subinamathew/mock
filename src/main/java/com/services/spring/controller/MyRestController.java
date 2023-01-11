package com.services.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @RequestMapping(value = "/")
    public String test() {
        return "End Points \n ";
    }


    public String getJWTToken() {
        MyJWTService myservice = new MyJWTService();
        WebClient client = WebClient.create();
        //WebClient.ResponseSpec responseSpec = client.get()
        //    .uri("http://example.com")
        //    .retrieve();
       // return responseSpec.bodyToMono(String.class).block();
       return myservice.getJWTSource();
    }


}
