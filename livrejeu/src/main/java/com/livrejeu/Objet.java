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
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Objet)) return false;
        Objet objet = (Objet) o;
        return this.nom.equals(objet.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}