package com.services.spring.services;

public class MyJWTService {

    public String getJWTSource() {
        return "https://httpbin.org/get";
    }

    //Identity: https://cloud.google.com/run/docs/securing/service-identity#identity-tokens
    public String getGCPIdentityURL() {
        return "http://metadata.google.internal/computeMetadata/v1/instance/service-accounts/default/identity?audience=";
    }

    public String GCPMetadata() {
        return "Metadata-Flavor: Google";
    }
}
