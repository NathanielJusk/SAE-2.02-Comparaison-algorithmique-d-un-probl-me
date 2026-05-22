package com.livrejeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.List;

public class Solution {
    private List<Page> pages;
    private int longueur;
    private int tempsParcours;
    private long tempsExecution;

    public Solution(List<Page> pages, long tempsExecution) {
        this.pages = pages;
        this.tempsExecution = tempsExecution;
        this.longueur = pages != null ? pages.size() : 0;
        // TODO: Calculer le temps de parcours total
    }

    public boolean estValide(LivreJeu livre) {
        // TODO: Vérifier le cheminement continu, le départ, la sortie et les objets
        // requis
        return false;
    }

    public List<Page> getPages() {
        return this.pages;
    }

    public int getLongueur() {
        return this.longueur;
    }

    public int getTempsParcours() {
        return this.tempsParcours;
    }

    public long getTempsExecution() {
        return this.tempsExecution;
    }

    @Override
    public String toString() {
        // TODO: Formater l'affichage de la solution
        return "Solution...";
    }
}