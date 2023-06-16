package com.example.capstonedesignproject;

import java.util.ArrayList;
import java.util.List;

public class ResultSaveResponse {
    private boolean isSuccess;
    private int code;
    private String message;
    private List<ResultDto> resultDtoList;

    public ResultSaveResponse() {
        // 기본 생성자
    }

    // Getters and Setters

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

    public List<ResultDto> getResultDtoList() {
        return resultDtoList;
    }

    public void setResultDtoList(List<ResultDto> resultDtoList) {
        this.resultDtoList = resultDtoList;
    }

    // Helper method to add a single ResultDto to the list
    public void addResultDto(ResultDto resultDto) {
        if (resultDtoList == null) {
            resultDtoList = new ArrayList<>();
        }
        resultDtoList.add(resultDto);
    }
}
