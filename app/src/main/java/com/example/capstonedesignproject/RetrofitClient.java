package com.example.capstonedesignproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://121.133.223.183:9000";

    public static LoginInterface getApiService(){
        return getInstance().create(LoginInterface.class);
    }

    public static SignUpInterface getRegisterApiService() {
        return getInstance().create(SignUpInterface.class);
    }
    public static TestInterface getTestApiService() {
        return getInstance().create(TestInterface.class);
    }
    public static ResultSaveInterface getResultSaveApiService() {
        return getInstance().create(ResultSaveInterface.class);
    }
    public static ExamEndInterface getExamEndApiService(){
        return getInstance().create(ExamEndInterface.class);
    }

    static Retrofit getInstance(){
        // 로그 인터셉터 추가
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                // 로그 출력
                System.out.println("Retrofit: " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // OkHttpClient에 로그 인터셉터 설정
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client) // OkHttpClient 설정
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }



}
