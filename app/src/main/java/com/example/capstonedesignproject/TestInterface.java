package com.example.capstonedesignproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TestInterface {

    @GET("/kisa/papers/{categoryNum}")
    Call<TestResponse> getPapers(

            @Header("ACCESS_TOKEN") String accessToken,
            @Path("categoryNum") String categoryNum,
            @Query("quantity") int quantity

    );


}

