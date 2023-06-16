package com.example.capstonedesignproject;

import java.io.File;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("uniqueId")
    private String uniqueId;

    @SerializedName("password")
    private String password;

    public RegisterRequest(String uniqueId, String password) {
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