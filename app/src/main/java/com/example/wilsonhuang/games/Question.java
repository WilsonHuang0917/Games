package com.example.wilsonhuang.games;

/**
 * Created by WilsonHuang on 2016/1/7.
 */
public class Question {
    private String questionContent;
    private boolean answer;

    public Question(String questionContent, boolean answer) {
        this.questionContent = questionContent;
        this.answer = answer;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
