package com.livrejeu;

import java.util.HashSet;
import java.util.Set;

public class Page {
    private int id;
    private Enigme enigme;
    private Set<Objet> objets;

    public Page(int id, Enigme enigme) {
        this.id = id;
        this.enigme = enigme;
        this.objets = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public Enigme getEnigme() {
        return enigme;
    }

    public Set<Objet> getObjets() {
        return objets;
    }

    public void ajouterObjet(Objet o) {
        objets.add(o);
    }
}