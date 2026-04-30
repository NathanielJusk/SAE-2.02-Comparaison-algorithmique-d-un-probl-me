package com.livrejeu;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private int id;
    private Enigme enigme;
    private List<Objet> objets;

    public Page(int id, Enigme enigme) {
        this.id = id;
        this.enigme = enigme;
        this.objets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Enigme getEnigme() {
        return enigme;
    }

    public List<Objet> getObjets() {
        return objets;
    }

    public void ajouterObjet(Objet o) {
        objets.add(o);
    }
}