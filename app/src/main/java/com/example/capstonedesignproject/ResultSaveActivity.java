package com.example.capstonedesignproject;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultSaveActivity extends AppCompatActivity {
    private static final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Iiwicm9sZXMiOiJST0xFX1VTRVIiLCJpYXQiOjE2ODY2OTcwMTMsImV4cCI6MTY4Njc4MzQxM30.BucuSVNmTAOGY6vxktIj23pCRPuu24ab_i4aZSmlB5E";
    private static final String categoryNum = "20200101";

    public static void saveResult(Context context, ResultSaveRequest request) {
        ResultSaveInterface resultSaveInterface = RetrofitClient.getResultSaveApiService();

        // 현재 로그인된 사용자의 userIdx 가져오기
        long userIdx = getLoggedInUserId(context);

        // 새로운 ResultSaveRequest 객체 생성하여 resultDtoList 및 userIdx 설정
        ResultSaveRequest updatedRequest = new ResultSaveRequest(userIdx, request.getResultDtoList());
        List<ResultDto> resultDtoList = updatedRequest.getResultDtoList();

        for (ResultDto resultDto : resultDtoList) {
            resultDto.setCategoryNum(categoryNum);
        }

        updatedRequest.setResultDtoList(resultDtoList);

        Call<ResultSaveResponse> call = resultSaveInterface.saveResult(ACCESS_TOKEN, updatedRequest);

        call.enqueue(new Callback<ResultSaveResponse>() {
            @Override
            public void onResponse(Call<ResultSaveResponse> call, Response<ResultSaveResponse> response) {
                if (response.isSuccessful()) {
                    ResultSaveResponse resultSaveResponse = response.body();
                    if (resultSaveResponse != null && resultSaveResponse.isSuccess()) {
                        // 저장 성공
                        handleSuccess();
                    } else {
                        // 저장 실패
                        String errorMessage = resultSaveResponse != null ? resultSaveResponse.getMessage() : "Unknown error";
                        handleFailure(errorMessage);
                    }
                } else {
                    // 통신 실패
                    handleFailure("Network error");
                }
            }

            @Override
            public void onFailure(Call<ResultSaveResponse> call, Throwable t) {
                // 통신 실패
                handleFailure(t.getMessage());
            }
        });
    }

    private static long getLoggedInUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getLong("userIdx", -1); // 기본값으로 -1을 반환하거나 다른 값으로 처리
    }

    private static void handleSuccess() {
        // 저장 성공에 대한 처리 추가
    }

    private static void handleFailure(String errorMessage) {
        // 저장 실패에 대한 처리 추가
    }
}
