package com.example.triviasdk;

public class Question {
    private String question;
    private String answer;
    private String level;

    public Question(String question, String answer, String level) {
        this.question = question;
        this.answer = answer;
        this.level = level;
    }

    // Getters and Setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

