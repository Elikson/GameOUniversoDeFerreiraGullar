package com.studiobt.descobrindomundoscomferreiragular;

import java.util.ArrayList;

/**
 * Created by Elikson Bastos on 07/05/2017.
 */

public class ManagerObras {

    private static ArrayList<Obra> obras = new ArrayList<>();

    public void addObra(String name, String content, ArrayList<Question> questions){
        obras.add(new Obra(name, content, questions));
    }

    public boolean getAnswear(int idObra, int idQuestion){
        return obras.get(idObra).getAnswear(idQuestion);
    }

    public String getQuestion(int idObra, int idQuestion){
        return obras.get(idObra).getQuestion(idQuestion);
    }

    public int getCountQuestions(int idObra){
        return obras.get(idObra).getQuestions().size();
    }

    public ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<>();
        for (Obra ob : obras) {
            names.add(ob.getName());
        }
        return names;
    }

    public String getName(int id){
        return obras.get(id).getName();
    }

    public String getContent(int id){
        return obras.get(id).getContent();
    }

    public void removeAll(){
        obras.clear();
    }

    public ArrayList<Obra> getAll(){
        return obras;
    }
}
