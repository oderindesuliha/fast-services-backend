package org.group6.fastservices.dtos.responses;

import lombok.Data;

@Data
public class ErrorResponse<T> {
    private String error;
    
    public ErrorResponse(String error) {
        this.error = error;
    }
}