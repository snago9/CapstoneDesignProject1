package com.example.capstonedesignproject;

import java.io.Serializable;

public class ResultDto implements Serializable {
    private long paperIdx;
    private Long userIdx;
    private String categoryNum;
    private String userAnswer;
    private boolean isCorrect;

    public ResultDto() {
        // 기본 생성자
    }

    // Getters and Setters

    public long getPaperIdx() {
        return paperIdx;
    }

    public void setPaperIdx(long paperIdx) {
        this.paperIdx = paperIdx;
    }

    public Long getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(Long userIdx) {
        this.userIdx = userIdx;
    }

    public String getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(String categoryNum) {
        this.categoryNum = categoryNum;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        this.isCorrect = correct;
    }
    }
