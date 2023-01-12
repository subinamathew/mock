package com.services.spring.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConjurService {
    
    @Value("${conjur.root}")
    private String conjurRootPath;
    @Value("${conjur.subpath}")
    private String conjurSubpath;
    @Value("${conjur.application.url}")
    private String conjurURL;
    @Value("${conjur.secret.name}")
    private String conjurSecretName;

    @Value("${gcp.service.name}")
    private String gcpServiceName;

    @Value("${gcp.stage.name}")
    private String gcpStageName;

    @Value("${gcp.project.id}")
    private String gcpProjectID;

    /**
     *  This can be used to get the complete URL
     **/
    public String conjurGcpAudience()  {
        final String url = this.conjurRootPath + '/' + this.conjurSubpath;
        return url;
    }
}
