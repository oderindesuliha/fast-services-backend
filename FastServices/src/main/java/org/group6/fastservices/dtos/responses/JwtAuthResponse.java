package org.group6.fastservices.dtos.responses;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}