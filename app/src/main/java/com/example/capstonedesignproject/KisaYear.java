package com.example.capstonedesignproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class KisaYear extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisa_year);

        ImageButton Button_moveToSign = findViewById(R.id.h_back);
        Button_moveToSign.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent( KisaYear.this, FirstSetting.class);
                startActivity(intent);
            }
        });
    }
}