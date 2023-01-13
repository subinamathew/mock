package com.services.spring.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConjurService {
  
    @Value("${conjur.audience}")
    private String conjurAudience;

    @Value("${conjur.authnpath}")
    private String gcpAuthnPath;

    @Value("${conjur.application.url}")
    private String conjurURL;

    @Value("${conjur.secret.name}")
    private String conjurSecretName;

    /**
     *  This can be used to get the complete URL
     **/
    public String getConjurGcpAudience(String gcpServiceIdentity)  {
        final String audience = this.conjurAudience + '/' + gcpServiceIdentity;
        return audience;
    }

    /**
     * This is used to get the Conjur URL.
     */
    public String getConjurURL() {
        final String conjurURL = this.conjurURL + '/' + this.gcpAuthnPath;
        return conjurURL;
    }

    public String getFormatedJWT (String JWTToken) {
        return "jwt=" + JWTToken; 
    }

}
