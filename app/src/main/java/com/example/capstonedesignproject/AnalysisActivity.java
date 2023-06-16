package com.example.capstonedesignproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;


import androidx.appcompat.app.AppCompatActivity;

public class AnalysisActivity extends AppCompatActivity {
    private PieChart p_chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        p_chart = findViewById(R.id.piechart);

        ImageButton to_home = findViewById(R.id.h_tohome);
        ImageButton to_cal= findViewById(R.id.h_tocalen);
        ImageButton to_analy = findViewById(R.id.h_toanaly);
        ImageButton to_user =findViewById(R.id.h_touser);

        to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalysisActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        to_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalysisActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        to_analy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalysisActivity.this, AnalysisActivity.class);
                startActivity(intent);
            }
        });
        to_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalysisActivity.this, Mypage.class);
                startActivity(intent);
            }
        });

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(25, "1과목"));
        entries.add(new PieEntry(35, "2과목"));
        entries.add(new PieEntry(40, "3과목"));
        entries.add(new PieEntry(40, "4과목"));
        entries.add(new PieEntry(40, "5과목"));



        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart");
        dataSet.setColors(Color.BLUE, Color.GREEN, Color.RED);
        dataSet.setValueTextColor(Color.BLACK);

        PieData data = new PieData(dataSet);
        p_chart.setData(data);
        p_chart.getDescription().setEnabled(false);
        p_chart.animateY(1000);
        p_chart.invalidate();


        Button analy_next = findViewById(R.id.analy_next);
        analy_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnalysisActivity.this, AnalysisActivity2.class);
                startActivity(intent);

            }
        });
    }
}



