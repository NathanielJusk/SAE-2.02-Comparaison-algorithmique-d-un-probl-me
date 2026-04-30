package com.livrejeu;

public class Enigme {
    private String texte;
    private int tempsResolution;

    public Enigme(String texte, int tempsResolution) {
        this.texte = texte;
        this.tempsResolution = tempsResolution;
    }

    public String getTexte() {
        return texte;
    }

    public int getTempsResolution() {
        return tempsResolution;
    }
}