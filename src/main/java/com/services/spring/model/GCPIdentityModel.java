package com.services.spring.model;

import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *  Based on: https://cloud.google.com/run/docs/securing/service-identity#identity-tokens
 *            https://cloud.google.com/compute/docs/instances/verifying-instance-identity
 */
@Component
public class GCPIdentityModel {

    private String GCP_METADATA_HEADER_KEY = "Metadata-Flavor";
    private String GCP_METADATA_HEADER_VALUE = "Google";
    private String GCP_VM_IDENTITY_URL= "http://localhost:8000";
    //format=full: the optional parameter that specifies that the project and instance details are included in the payload. 
    private String GCP_AUDIENCE_FORMAT = "?audience=%s&format=full";

    @Value("${gcp.service.name}")
    private String gcpServiceName;

    @Value("${gcp.stage.name}")
    private String gcpStageName;

    @Value("${gcp.project.id}")
    private String gcpProjectID;

    public String getGCPVMIdentityURL() {
        return GCP_VM_IDENTITY_URL;
    }

    public String getGCPMetadataKey() {
        return GCP_METADATA_HEADER_KEY;
    }
    public String getGCPMetadataValue() {
        return GCP_METADATA_HEADER_VALUE;
    }

    /**
     *  This can be used to get the complete URL
     *  @param audience: This is the request URL.
     *  @throws UnsupportedEncodingException
     */
    public String getGCPRequestURL(String audience) throws UnsupportedEncodingException {
		final String query = String.format(GCP_AUDIENCE_FORMAT, audience );
        final String url = this.GCP_VM_IDENTITY_URL + query;
        return url;
    }
    

    /**
     *  This can be used to get the the GCP identity
     */
    public String getGCPServiceIdentity() {
		final String identity = gcpServiceName + "/google/" + gcpStageName + "/" + gcpProjectID;
        return identity;
    }

}
