package com.example.capstonedesignproject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestResponse {
    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private List<Result> results;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<Result> getResults() {
        return results;
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

        public Integer getNumber() {
            return number;
        }

        public String getQuestion() {
            return question;
        }

        public String getChoiceA() {
            return choiceA;
        }

        public String getChoiceB() {
            return choiceB;
        }

        public String getChoiceC() {
            return choiceC;
        }

        public String getChoiceD() {
            return choiceD;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public String getCategoryNum() {
            return categoryNum;
        }

        private String selectedAnswer;

        public String getSelectedAnswer() {
            return selectedAnswer;
        }

        public void setSelectedAnswer(String selectedAnswer) {
            this.selectedAnswer = selectedAnswer;
        }

    }
}
