package com.example.capstonedesignproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FirstSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_setting);

        ImageButton Button_moveToHome = findViewById(R.id.h_next);
        Button_moveToHome.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent( FirstSetting.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Button_moveToKisaname = findViewById(R.id.h_Kisaname_btn);
        Button_moveToKisaname.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent( FirstSetting.this, KisaName.class);
                startActivity(intent);
            }
        });

        ImageButton Button_moveToKisayear = findViewById(R.id.h_Kisayear_btn);
        Button_moveToKisayear.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent( FirstSetting.this, KisaYear.class);
                startActivity(intent);
            }
        });

        ImageButton Button_moveToKisaturn = findViewById(R.id.h_Kisaturn_btn);
        Button_moveToKisaturn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent( FirstSetting.this, KisaTurn.class);
                startActivity(intent);
            }
        });
    }
}