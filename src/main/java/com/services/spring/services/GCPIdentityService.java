package com.services.spring.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;

/**
 *  Based on: https://cloud.google.com/run/docs/securing/service-identity#identity-tokens
 */
@Component
public class GCPIdentityService {

    private String GCP_METADATA_HEADER_KEY = "Metadata-Flavor: Google";
    private String GCP_METADATA_HEADER_VALUE = "Metadata-Flavor: Google";
    private String GCP_VM_IDENTITY_URL= "http://metadata.google.internal/computeMetadata/v1/instance/service-accounts/default/identity";
    private String GCP_FORMAT = "?audience=%s&format=full";

    public String GCPVMIdentityURL() {
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
    public String GCPRequestURL(String audience) throws UnsupportedEncodingException {
		final String query = String.format(GCP_FORMAT, URLEncoder.encode(audience, StandardCharsets.US_ASCII.toString()));
        final String url = this.GCP_VM_IDENTITY_URL + query;
        return url;
    }
    
}
