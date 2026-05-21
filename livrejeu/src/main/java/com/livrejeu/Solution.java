package com.livrejeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    private List<Page> pages;
    private int longueur;
    private int tempsParcours;
    private LivreJeu livre;
    private long tempsExecution;

    public Solution(List<Page> pages, long tempsExecution) {
        this.pages = pages;
        this.tempsExecution = tempsExecution;
    }


    public List<Page> getPages() {
        // TODO
        return pages;
    }

    public int getLongueur() {
        // TODO
        return longueur;
    }

    public double getTempsParcours() {
        // TODO
        return tempsParcours;
    }
    


    public LivreJeu getLivre() {
        return livre;
    }


    public void setLivre(LivreJeu livre) {
        this.livre = livre;
    }


    public long getTempsExecution() {
        return tempsExecution;
    }


    public void setTempsExecution(long tempsExecution) {
        this.tempsExecution = tempsExecution;
    }


    public boolean estValide(LivreJeu livre) {
        // TODO
        // 1. pages.get(0) == livre.getPageDepart()
        // 2. pages.get(derniere) == livre.getPageSortie()
        // 3. chaque page successive est bien reliée
        // 4. objetsCollectes contient tous les objetsRequis
        return false;
    }

    @Override
    public String toString() {
        // TODO
        return null;
    }
}
