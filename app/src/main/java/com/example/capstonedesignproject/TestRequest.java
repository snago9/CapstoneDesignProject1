package com.example.capstonedesignproject;

import com.google.gson.annotations.SerializedName;

public class TestRequest {
    private String ACCESS_TOKEN;
    private boolean isSuccess;
    private int code;
    private String message;
    private Result result;

    public TestRequest(String ACCESS_TOKEN) {
        this.ACCESS_TOKEN = ACCESS_TOKEN;
    }

    public String getACCESS_TOKEN() {
        return ACCESS_TOKEN;
    }

    public void setACCESS_TOKEN(String ACCESS_TOKEN) {
        this.ACCESS_TOKEN = ACCESS_TOKEN;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
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

    public static class Result {
        @SerializedName("paperIdx")
        private Long paperIdx;

        @SerializedName("number")
        private Integer number;

        @SerializedName("question")
        private String question;

        @SerializedName("choiceA")
        private String choiceA;

        @SerializedName("choiceB")
        private String choiceB;

        @SerializedName("choiceC")
        private String choiceC;

        @SerializedName("choiceD")
        private String choiceD;

        @SerializedName("correctAnswer")
        private String correctAnswer;

        @SerializedName("categoryNum")
        private String categoryNum;

        public Long getPaperIdx() {
            return paperIdx;
        }

        public void setPaperIdx(Long paperIdx) {
            this.paperIdx = paperIdx;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getChoiceA() {
            return choiceA;
        }

        public void setChoiceA(String choiceA) {
            this.choiceA = choiceA;
        }

        public String getChoiceB() {
            return choiceB;
        }

        public void setChoiceB(String choiceB) {
            this.choiceB = choiceB;
        }

        public String getChoiceC() {
            return choiceC;
        }

        public void setChoiceC(String choiceC) {
            this.choiceC = choiceC;
        }

        public String getChoiceD() {
            return choiceD;
        }

        public void setChoiceD(String choiceD) {
            this.choiceD = choiceD;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public void setCorrectAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
        }

        public String getCategoryNum() {
            return categoryNum;
        }

        public void setCategoryNum(String categoryNum) {
            this.categoryNum = categoryNum;
        }
    }
}
