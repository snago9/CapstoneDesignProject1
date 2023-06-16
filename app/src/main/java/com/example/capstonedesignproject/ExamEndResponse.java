package com.example.capstonedesignproject;

public class ExamEndResponse {
    private boolean isSuccess;
    private int code;
    private String message;
    private StatisticsData result;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public StatisticsData getResult() {
        return result;
    }
}


