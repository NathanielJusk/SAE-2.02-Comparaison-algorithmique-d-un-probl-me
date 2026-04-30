package com.livrejeu;

public class Objet {
    private String id;
    private String nom;

    public Objet(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}