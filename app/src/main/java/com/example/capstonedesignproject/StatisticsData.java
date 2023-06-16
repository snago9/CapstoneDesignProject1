package com.example.capstonedesignproject;

import com.google.gson.annotations.SerializedName;

public class StatisticsData {
    @SerializedName("correct")
    private static Long correct;

    @SerializedName("total")
    private String total;

    public static Long getCorrect() {
        return correct;
    }

    public String getTotal() {
        return total;
    }
}
