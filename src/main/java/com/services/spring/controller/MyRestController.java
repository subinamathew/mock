package com.services.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import com.services.spring.services.ConjurService;
import com.services.spring.services.GCPIdentityService;

@RestController
public class MyRestController {

    @Autowired
    GCPIdentityService gcpIdentityService;

    @Autowired
    ConjurService conjurService;

    /**
     * This is used to get the JWT from Google Metadata Server
     */
    @RequestMapping(value ="/")
    public String getGCPToken() {
        RestTemplate restTemplate = new RestTemplate();
        String JWTToken = "";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(gcpIdentityService.getGCPMetadataKey(), gcpIdentityService.getGCPMetadataValue());
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            String gcpServiceIdentity = gcpIdentityService.getGCPServiceIdentity();
            String requestURL = gcpIdentityService.getGCPRequestURL(conjurService.getConjurGcpAudience(gcpServiceIdentity));
            ResponseEntity<String> response = restTemplate.exchange(requestURL,HttpMethod.GET, requestEntity, String.class);
            JWTToken = response.getBody();
        } catch (Exception  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return JWTToken;
    }



    /**
     * This is used to get the Conjur Authetication Token
     * @return Conjur Authentication Token. This is valid for 8-10 minutes normally.
     */
    @RequestMapping(value ="/conjur/{JWTToken}")
    public String getConjurToken(@PathVariable String JWTToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //header accepts base64
        HttpEntity<String> requestEntity = new HttpEntity<>(conjurService.getFormatedJWT(JWTToken),headers);
        RestTemplate restTemplate = new RestTemplate();
        String conjurAuthToken = "";
        String conjurUrl = conjurService.getConjurURL();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(conjurUrl,requestEntity, String.class);
            conjurAuthToken = response.getBody();
        } catch (Exception  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conjurAuthToken;
    }

    @RequestMapping(value ="/accessSecret")
    public String getSecret(String conjurToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(gcpIdentityService.getGCPMetadataKey(), gcpIdentityService.getGCPMetadataValue());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //header accepts base64

        HttpEntity<String> requestEntity = new HttpEntity<String>(conjurToken,headers);
        RestTemplate restTemplate = new RestTemplate();
        String conjurAuthToken = "";
        String conjurUrl = conjurService.getConjurURL();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(conjurUrl,requestEntity, String.class);
            conjurAuthToken = response.getBody();
        } catch (Exception  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conjurAuthToken;
    }


}
