package com.example.capstonedesignproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText uniqueIdEditText, passwordEditText;
    private CheckBox rememberIdCheckbox;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uniqueIdEditText = findViewById(R.id.h_id);
        passwordEditText = findViewById(R.id.h_password);
        rememberIdCheckbox = findViewById(R.id.h_cb);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        boolean rememberId = sharedPreferences.getBoolean("remember_id", false);
        rememberIdCheckbox.setChecked(rememberId);

        String savedId = sharedPreferences.getString("saved_id", "");
        uniqueIdEditText.setText(savedId);

        findViewById(R.id.h_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        findViewById(R.id.h_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), sign_up.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String uniqueId = uniqueIdEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (uniqueId.isEmpty()) {
            uniqueIdEditText.setError("아이디를 입력하세요.");
            uniqueIdEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("비밀번호를 입력하세요.");
            passwordEditText.requestFocus();
            return;
        }

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("로그인 중...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        LoginRequest loginRequest = new LoginRequest(uniqueId, password);
        RetrofitClient apiClient = new RetrofitClient();
        Call<LoginResponse> call = apiClient.getApiService().getLoginResponse(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null) {
                    boolean isSuccess = response.body().isSuccess();
                    String message = response.body().getMessage();

                    if (isSuccess) {
                        Long userIdx = response.body().getResult().getUserIdx();
                        String accessToken = response.body().getResult().getAccessToken();

                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putLong("userIdx", userIdx);
                        editor.putString("accessToken", accessToken);

                        boolean rememberId = rememberIdCheckbox.isChecked();
                        if (rememberId) {
                            editor.putBoolean("remember_id", true);
                            editor.putString("saved_id", uniqueId);
                        } else {
                            editor.putBoolean("remember_id", false);
                            editor.remove("saved_id");
                        }

                        editor.apply();

                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            private long getLoggedInUserId() {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                return sharedPreferences.getLong("userIdx", -1); // 기본값으로 -1을 반환하거나 다른 값으로 처리
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "네트워크 오류 발생", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
