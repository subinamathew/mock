package com.services.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.services.spring.service.ConjurService;
import com.services.spring.service.GCPService;

@RestController
public class MyRestController {

    @Autowired
    GCPService gcpService;

    @Autowired
    ConjurService conjurService;

    /**
     * This is used to get the JWT from Google Metadata Server
     */
    @RequestMapping(value ="/")
    public String getRoot() {
        return "/all, /gcp, /conjur/{JWTToken}, /accessSecret";
    }

    /**
     * This is used to get all
     */
    @RequestMapping(value ="/gcp")
    public String getGCPToken() {
        String JWTToken = gcpService.generateGCPToken();
        return JWTToken;

    }

    /**
     * This is used to get the JWT from Google Metadata Server
     */
    @RequestMapping(value ="/all")
    public String getConjurTokenAll() {
        return conjurService.generateConjurToken(gcpService.generateGCPToken());
    }

    /**
     * This is used to get the Conjur Authetication Token
     * @return Conjur Authentication Token. This is valid for 8-10 minutes normally.
     */
    @RequestMapping(value ="/conjur/{JWTToken}")
    private String getConjurToken(@PathVariable String JWTToken) {
        String conjurAuthToken = conjurService.generateConjurToken(JWTToken);
        return conjurAuthToken;
    }

    @RequestMapping(value ="/accessSecret/{secretVariable}")
    public String getSecret(@PathVariable String secretVariable) {
        System.out.println(secretVariable);
        return getSecretFromConjur(getConjurTokenAll(), secretVariable);
    }

    private String getSecretFromConjur(String conjurToken, String secretVariable ) {
        return conjurService.getConjurSecret(conjurToken, secretVariable);
    }


}
