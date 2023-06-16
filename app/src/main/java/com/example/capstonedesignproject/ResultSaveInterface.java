package com.example.capstonedesignproject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ResultSaveInterface {
    @POST("/kisa/statistics/result")
    Call<ResultSaveResponse> saveResult(
            @Header("ACCESS_TOKEN") String accessToken,
            @Body ResultSaveRequest resultSaveRequest
    );
}
