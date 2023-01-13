package com.services.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.services.spring.model.ConjurConnector;
import com.services.spring.model.GCPIdentityModel;

@Component
public class GCPService {

    @Autowired
    GCPIdentityModel gcpIdentityModel;

    @Autowired
    ConjurConnector conjurConnector;
    
    /**
     * This is used to get the JWT from Google Metadata Server
     */

    public String generateGCPToken() {
        RestTemplate restTemplate = new RestTemplate();
        String JWTToken = "";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(gcpIdentityModel.getGCPMetadataKey(), gcpIdentityModel.getGCPMetadataValue());
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            String gcpServiceIdentity = gcpIdentityModel.getGCPServiceIdentity();
            String requestURL = gcpIdentityModel.getGCPRequestURL(conjurConnector.getConjurGcpAudience(gcpServiceIdentity));
            ResponseEntity<String> response = restTemplate.exchange(requestURL,HttpMethod.GET, requestEntity, String.class);
            JWTToken = response.getBody();
        } catch (Exception  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return JWTToken;
    }
}
