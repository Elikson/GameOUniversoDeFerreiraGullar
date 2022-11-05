package com.studiobt.descobrindomundoscomferreiragular;

/**
 * Created by Elikson Bastos on 07/05/2017.
 */

public class Question {
    private String question;
    private boolean answear;

    public Question(String question, boolean answear) {
        this.question = question;
        this.answear = answear;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswear() {
        return answear;
    }

    public void setAnswear(boolean answear) {
        this.answear = answear;
    }
}