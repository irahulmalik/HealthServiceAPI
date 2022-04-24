package com.example.project.Model;

public class RequestResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RequestResponse() {
    }

    public RequestResponse(String token) {
        this.token = token;
    }
}
