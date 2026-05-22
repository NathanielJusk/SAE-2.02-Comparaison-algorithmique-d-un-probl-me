package com.livrejeu;

public class Enigme {
    private String texte;
    private int tempsResolution;

    public Enigme(String texte, int tempsResolution) {
        this.texte = texte;
        this.tempsResolution = tempsResolution;
    }

    public int getTempsResolution() {
        return this.tempsResolution;
    }

    public String getTexte() {
        return this.texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public void setTempsResolution(int temps) {
        this.tempsResolution = temps;
    }
}