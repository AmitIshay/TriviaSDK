package com.example.triviasdk;

public class GenerateQuestionRequest {
    private String topic;
    private String level;

    public GenerateQuestionRequest(String topic, String level) {
        this.topic = topic;
        this.level = level;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
