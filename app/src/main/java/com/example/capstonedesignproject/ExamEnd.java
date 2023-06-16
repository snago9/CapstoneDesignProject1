package com.example.capstonedesignproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamEnd extends AppCompatActivity {

    private static final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Iiwicm9sZXMiOiJST0xFX1VTRVIiLCJpYXQiOjE2ODY2OTcwMTMsImV4cCI6MTY4Njc4MzQxM30.BucuSVNmTAOGY6vxktIj23pCRPuu24ab_i4aZSmlB5E";
    private static final String TAG = "ExamEnd";
    private int correctAnswers; // 맞춘 문제 수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_end);

        ImageButton nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 틀린 문제, 맞춘 문제를 보여주는 화면으로 이동
                // 필요한 동작 추가
            }
        });

        ImageButton moveToSelectTestButton = findViewById(R.id.h_back);
        moveToSelectTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 시험 고르는 페이지로 이동
                finish();
            }
        });

        // 사용자 인덱스 받아오기
        long userIdx = getLoggedInUserId();

        // API 호출하여 맞춘 문제 수 조회
        getCorrectAnswerCount(userIdx);
    }

    private void getCorrectAnswerCount(long userIdx) {
        ExamEndInterface examEndInterface = RetrofitClient.getExamEndApiService();
        Call<ExamEndResponse> call = examEndInterface.getStatistics(ACCESS_TOKEN, userIdx);
        call.enqueue(new Callback<ExamEndResponse>() {
            @Override
            public void onResponse(Call<ExamEndResponse> call, Response<ExamEndResponse> response) {
                if (response.isSuccessful()) {
                    ExamEndResponse examEndResponse = response.body();
                    if (examEndResponse != null && examEndResponse.isSuccess()) {
                        StatisticsData resultData = examEndResponse.getResult();
                        if (resultData != null && resultData.getCorrect() != null) {
                            correctAnswers = Math.toIntExact(resultData.getCorrect().longValue());
                            displayScore();
                        } else {
                            Log.e(TAG, "Result data or correct answers are null");
                            displayError();
                        }

                    } else {
                        String errorMessage = getErrorMessage(response);
                        Log.e(TAG, "Failed to retrieve statistics: " + errorMessage);
                        displayError();
                    }
                } else {
                    String errorMessage = response.message();
                    Log.e(TAG, "Failed to retrieve statistics: " + errorMessage);
                    displayError();
                }
            }

            @Override
            public void onFailure(Call<ExamEndResponse> call, Throwable t) {
                Log.e(TAG, "Failed to retrieve statistics: " + t.getMessage());
                displayError();
            }
        });
    }

    private void displayScore() {
        TextView scoreTextView = findViewById(R.id.exam_score);
        scoreTextView.setText(getString(R.string.exam_score, correctAnswers));
    }

    private void displayError() {
        Toast.makeText(ExamEnd.this, "Failed to retrieve statistics", Toast.LENGTH_SHORT).show();
    }

    private long getLoggedInUserId() {
        // Retrieve the logged-in user ID
        return 6; // Replace with your actual implementation
    }

    private String getErrorMessage(Response<?> response) {
        // Extract error message from the response
        // Replace with your actual implementation
        return "Error";
    }
}
