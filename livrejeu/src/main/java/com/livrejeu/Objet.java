package com.livrejeu;

import java.util.Objects;

public class Objet {
    private String nom;

    public Objet(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    @Override
    public boolean equals(Object o) {
        //todo;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}