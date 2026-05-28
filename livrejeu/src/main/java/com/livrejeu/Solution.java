package com.livrejeu;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
    private List<Page> pages;
    private int longueur;
    private int tempsParcours;
    private long tempsExecution;

    public Solution(List<Page> pages, long tempsExecution) {
        this.pages = pages;
        this.tempsExecution = tempsExecution;
        this.longueur = pages != null ? pages.size() : 0;
        this.tempsParcours = 0;
        if (pages != null) {
            for (Page page : pages) {
                this.tempsParcours += page.getEnigme().getTempsResolution();
            }
        }
    }

    public boolean estValide(LivreJeu livre) {
        // verification de premiere page = Page depart
        if (!(pages.get(0).equals(livre.getPageDepart()))) {
            return false;
        }
        // verification de derniere page = Page sortie
        if (!(pages.get(pages.size() - 1).equals(livre.getPageSortie()))) {
            return false;
        }
        // fonction estChaine du Tp1
        for (int i = 0; i < pages.size() - 1; i++) {
            if (!(livre.getGraphe().containsEdge(pages.get(i), pages.get(i + 1)))) {
                return false;
            }
        }
        // verification objet collecté est l'objet requis
        Set<Objet> objetsCollectes = new HashSet<Objet>();
        for (Page p : pages) {
            objetsCollectes.addAll(p.getObjets());
        }
        return objetsCollectes.containsAll(livre.getObjetsRequis());
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
        StringBuilder sb = new StringBuilder();
        sb.append("=== Solution ===\n");
        sb.append("Longueur       : ").append(longueur).append(" pages\n");
        sb.append("Temps parcours : ").append(tempsParcours).append(" sec\n");
        sb.append("Temps exécution: ").append(tempsExecution).append(" ms\n");
        sb.append("Chemin         : ");
        for (int i = 0; i < pages.size(); i++) {
            sb.append("Page ").append(pages.get(i).getId());
         sb.append(" : Temps Enigme ").append(pages.get(i).getEnigme().getTempsResolution());
            if (i < pages.size() - 1)
                sb.append(" -> ");
        }
        sb.append("\n================");
        return sb.toString();
    }
}