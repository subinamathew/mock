package com.services.spring.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConjurConnector {
  
    @Value("${conjur.audience}")
    private String conjurAudience;

    @Value("${conjur.authnpath}")
    private String gcpAuthnPath;

    @Value("${conjur.projectpath}")
    private String conjurProjectPath;

    @Value("${conjur.application.url}")
    private String conjurURL;

    private String CONJUR_HEADER_KEY = "Authorization";
    private String CONJUR_HEADER_VALUE = "Token token=";
    private String CONJUR_SECRET_DEFAULT = "secrets/default";

 
    public String getConjurMetadataKey() {
        return CONJUR_HEADER_KEY;
    }

    public String getConjurMetadataValue() {
        return CONJUR_HEADER_VALUE;
    }

    /**
     *  This can be used to get the complete URL
     **/
    public String getConjurGcpAudience(String gcpServiceIdentity)  {
        final String audience = this.conjurAudience + "/" + gcpServiceIdentity;
        return audience;
    }

    /**
     * This is used to get the Conjur URL.
     */
    public String getConjurAuthURL() {
        final String conjurURL = this.conjurURL + "/" + this.gcpAuthnPath;
        return conjurURL;
    }

    /**
     * This is used to get the Conjur URL.
     */
    public String getConjurURL() {
        final String conjurURL = this.conjurURL;
        return conjurURL;
    }

    /**
     * This is used to get the Conjur URL.
     * @param string
     */
    public String getConjurSecretURL(String secretVariable, String gcpIdentity ) {
        final String conjurURL = this.conjurURL + "/" + this.conjurProjectPath + "/" + gcpIdentity + "/" + this.CONJUR_SECRET_DEFAULT + "/" + secretVariable ;
        return conjurURL;
    }
    

    public String getFormatedJWT (String JWTToken) {
        return "jwt=" + JWTToken; 
    }

}
