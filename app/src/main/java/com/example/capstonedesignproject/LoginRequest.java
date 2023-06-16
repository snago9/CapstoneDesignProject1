package com.example.capstonedesignproject;



public class LoginRequest {
    private String uniqueId;
    private String password;

    public LoginRequest(String uniqueId, String password) {
        this.uniqueId = uniqueId;
        this.password = password;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

