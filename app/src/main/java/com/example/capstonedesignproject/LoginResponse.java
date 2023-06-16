package com.example.capstonedesignproject;

public class LoginResponse {
    private boolean isSuccess;
    private int code;
    private String message;
    private Result result;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        private Long userIdx;
        private String accessToken;

        public Long getUserIdx() {
            return userIdx;
        }

        public void setUserIdx(Long userIdx) {
            this.userIdx = userIdx;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}

