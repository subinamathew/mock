package com.services.spring.services;

import org.springframework.beans.factory.annotation.Value;

public class ConjurService {
    
    @Value("${conjur.root}")
    private String CONJUR_ROOT;
    @Value("${conjur.subpath}")
    private String CONJUR_SUBPATH;
    @Value("${conjur.application.url}")
    private String CONJUR_URL;

    @Value("${gcp.service.name}")
    private String GCP_SERVICE_NAME;
    @Value("${gcp.stage.name}")
    private String GCP_STAGE_NAME;
    @Value("${gcp.project.id}")
    private String GCP_PROJECTID;
}
