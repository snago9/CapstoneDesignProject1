package com.example.capstonedesignproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelectTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test);

        ImageButton Button_moveToHome = findViewById(R.id.h_back);
        Button_moveToHome.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent( SelectTest.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button_moveToExam= findViewById(R.id.test_start);
        Button_moveToExam.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent( SelectTest.this, ExamPage.class);
                startActivity(intent);
            }
        });

        ImageButton Button_moveToEST= findViewById(R.id.edit_test);
        Button_moveToEST.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent( SelectTest.this, EditSelectedTest.class);
                startActivity(intent);
            }
        });
    }
}