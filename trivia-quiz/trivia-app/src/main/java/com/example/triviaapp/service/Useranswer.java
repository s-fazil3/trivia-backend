package com.example.triviaapp.service;

public class Useranswer {
    private Long questionId;
    private String selectedOption; // "A", "B", "C", "D"

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }


// Getters and Setters
}
