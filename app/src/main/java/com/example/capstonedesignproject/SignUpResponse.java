package com.example.capstonedesignproject;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private Result result;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result getResult() {
        return result;
    }

    public static class Result {
        @SerializedName("userIdx")
        private int userIdx;

        public int getUserIdx() {
            return userIdx;
        }
    }
}
