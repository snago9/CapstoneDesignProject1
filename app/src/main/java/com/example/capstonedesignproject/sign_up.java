package com.example.capstonedesignproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class sign_up extends AppCompatActivity {

    private EditText  uniqueIdEditText, passwordEditText, passwordConfirmEditText;
    private ImageButton registerButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        uniqueIdEditText = findViewById(R.id.h_id);
        passwordEditText = findViewById(R.id.h_password);
        registerButton = findViewById(R.id.btn_signup);
        passwordConfirmEditText = findViewById(R.id.pw_check);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String uniqueId = uniqueIdEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String passwordConfirm = passwordConfirmEditText.getText().toString().trim();




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





        progressDialog = new ProgressDialog(sign_up.this);
        progressDialog.setMessage("가입 중...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JoinReqDto joinReqDto = new JoinReqDto(uniqueId, password);
        RegisterRequest registerRequest = new RegisterRequest(uniqueId,password);
        Retrofit apiClient = RetrofitClient.getInstance();
        RetrofitClient ApiClient = null;
        Call<SignUpResponse> call = ApiClient.getRegisterApiService().getSignUpResponse(registerRequest);



        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null) {
                    boolean isSuccess = response.body().isSuccess();
                    String message = response.body().getMessage();

                    if (isSuccess) {
                        // 가입 성공
                        Toast.makeText(sign_up.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // 가입 실패
                        Toast.makeText(sign_up.this, message, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(sign_up.this, "가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(sign_up.this, "네트워크 오류 발생", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
