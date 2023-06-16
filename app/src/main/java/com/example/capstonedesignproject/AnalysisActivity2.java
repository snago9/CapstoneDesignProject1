package com.example.capstonedesignproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;


public class AnalysisActivity2 extends AppCompatActivity {
    private LineChart l_chart;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            ImageButton to_home = findViewById(R.id.h_tohome);
            ImageButton to_cal= findViewById(R.id.h_tocalen);
            ImageButton to_analy = findViewById(R.id.h_toanaly);
            ImageButton to_user =findViewById(R.id.h_touser);

            to_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AnalysisActivity2.this, HomeActivity.class);
                    startActivity(intent);
                }
            });
            to_cal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AnalysisActivity2.this, CalendarActivity.class);
                    startActivity(intent);
                }
            });
            to_analy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AnalysisActivity2.this, AnalysisActivity.class);
                    startActivity(intent);
                }
            });
            to_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AnalysisActivity2.this, Mypage.class);
                    startActivity(intent);
                }
            });

            setContentView(R.layout.activity_analysis_2);
            l_chart = findViewById(R.id.linechart);
            ArrayList<Entry> values = new ArrayList<>();

            for (int i = 0; i < 10; i++) {

                float val = (float) (Math.random() * 10);
                values.add(new Entry(i, val));
            }
            LineDataSet set1;
            set1 = new LineDataSet(values, "DataSet 1");

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);


            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);


            l_chart.setData(data);


            Button analy_back = findViewById(R.id.analy_back);
            analy_back.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick (View view){
                    Intent intent = new Intent( AnalysisActivity2.this, AnalysisActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

