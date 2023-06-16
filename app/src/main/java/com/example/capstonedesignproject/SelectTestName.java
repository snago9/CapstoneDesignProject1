package com.example.capstonedesignproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelectTestName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test_name);

        ImageButton Button_moveToST = findViewById(R.id.h_back);
        Button_moveToST.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent( SelectTestName.this, SelectTest.class);
                startActivity(intent);
            }
        });
    }
}