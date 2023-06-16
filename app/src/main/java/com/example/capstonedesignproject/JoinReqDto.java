package com.example.capstonedesignproject;

import com.google.gson.annotations.SerializedName;

public class JoinReqDto {
    @SerializedName("uniqueId")
    private String uniqueId;

    @SerializedName("password")
    private String password;





    public JoinReqDto(String uniqueId, String password) {
        this.uniqueId = uniqueId;
        this.password = password;


    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getPassword() {
        return password;
    }



}
