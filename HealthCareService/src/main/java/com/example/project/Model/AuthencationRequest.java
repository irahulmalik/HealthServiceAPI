package com.example.project.Model;

public class AuthencationRequest {
    private String user_name;
    private String password;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthencationRequest() {
    }

    public AuthencationRequest(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }
}
