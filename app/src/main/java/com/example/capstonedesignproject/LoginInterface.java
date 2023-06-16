package com.example.capstonedesignproject;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;

public interface LoginInterface {

        @POST("/kisa/users/login")
        Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);


}
