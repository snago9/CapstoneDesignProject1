package com.example.capstonedesignproject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResultSaveRequest implements Serializable {
    @SerializedName("userIdx")
    private long userIdx;

    @SerializedName("resultDtoList")
    private List<ResultDto> resultDtoList;

    public ResultSaveRequest(long userIdx, List<ResultDto> resultDtoList) {
        this.userIdx = userIdx;
        this.resultDtoList = resultDtoList;
    }

    public long getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(long userIdx) {
        this.userIdx = userIdx;
    }

    public List<ResultDto> getResultDtoList() {
        return resultDtoList;
    }

    public void setResultDtoList(List<ResultDto> resultDtoList) {
        this.resultDtoList = resultDtoList;
    }
}
