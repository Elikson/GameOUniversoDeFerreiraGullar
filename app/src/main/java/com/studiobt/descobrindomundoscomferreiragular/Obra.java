package com.studiobt.descobrindomundoscomferreiragular;

import java.util.ArrayList;

/**
 * Created by Elikson Bastos on 07/05/2017.
 */

public class Obra {
    private String name;
    private String content;
    private ArrayList<Question> questions = new ArrayList<>();

    public Obra(String name, String content, ArrayList<Question> questions) {
        this.name = name;
        this.content = content;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public boolean getAnswear(int id){
        return questions.get(id).isAnswear();
    }

    public String getQuestion(int id){
        return questions.get(id).getQuestion();
    }
}
