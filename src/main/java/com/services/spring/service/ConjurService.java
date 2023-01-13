package com.services.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.services.spring.model.ConjurConnector;
import com.services.spring.model.GCPIdentityModel;

@Component
public class ConjurService {

    @Autowired
    GCPIdentityModel gcpIdentityModel;

    @Autowired
    ConjurConnector conjurConnector;

    public String generateConjurToken(String JWTToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //header accepts base64
        HttpEntity<String> requestEntity = new HttpEntity<>(conjurConnector.getFormatedJWT(JWTToken),headers);
        RestTemplate restTemplate = new RestTemplate();
        String conjurAuthToken = "";
        String conjurUrl = conjurConnector.getConjurAuthURL();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(conjurUrl,requestEntity, String.class);
            conjurAuthToken = response.getBody();
        } catch (Exception  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conjurAuthToken;
    }

    public String getConjurSecret(String JWTToken) {
        HttpHeaders headers = new HttpHeaders();
        String conjurAuthValue = conjurConnector.getConjurMetadataValue() + JWTToken;
        headers.set(conjurConnector.getConjurMetadataKey(), conjurAuthValue);
        //header accepts base64
        HttpEntity<String> requestEntity = new HttpEntity<>(conjurConnector.getFormatedJWT(JWTToken),headers);
        RestTemplate restTemplate = new RestTemplate();
        String conjurAuthToken = "";
        String conjurUrl = conjurConnector.getConjurAuthURL();
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
