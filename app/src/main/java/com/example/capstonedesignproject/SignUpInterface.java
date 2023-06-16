package com.example.capstonedesignproject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpInterface {

    @POST("/kisa/users/signup/test")
    Call<SignUpResponse> getSignUpResponse(@Body RegisterRequest RegisterRequest);


}
