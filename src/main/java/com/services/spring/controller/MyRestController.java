package com.services.spring.controller;

import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.services.spring.services.GCPIdentityService;

@RestController
public class MyRestController {

    @Autowired
    GCPIdentityService gcpIdentityService;

    @RequestMapping(value ="/")
    public String getGCPToken() {
        RestTemplate restTemplate = new RestTemplate();
        String JWTToken = "";
        try {
            JWTToken = gcpIdentityService.GCPRequestURL("myaddress");
            //JWTToken = restTemplate.getForObject(gcpIdentityService.GCPRequestURL(gcpIdentityService.getJWTSource()), String.class);
        } catch (RestClientException | UnsupportedEncodingException  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return JWTToken;
    }


}
