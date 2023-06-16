package com.example.capstonedesignproject;

public class LoggedInUser {
    private long userIdx;

    public LoggedInUser(long userIdx) {
        this.userIdx = userIdx;
    }

    public long getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(long userIdx) {
        this.userIdx = userIdx;
    }
}
