package com.example.capstonedesignproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ExamEndInterface {

    @GET("kisa/statistics/{userIdx}")
    Call<ExamEndResponse> getStatistics(
            @Header("ACCESS_TOKEN") String accessToken,
            @Path("userIdx") long userIdx
    );
}
