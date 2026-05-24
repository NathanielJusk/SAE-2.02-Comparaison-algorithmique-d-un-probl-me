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
        this.tempsParcours = 0;
        if (pages != null) {
            for (Page page : pages) {
                this.tempsParcours += page.getEnigme().getTempsResolution();
            }
        }
    }

    public boolean estValide(LivreJeu livre) {
        //verification de premiere page = Page depart
        if(!(pages.get(0).equals(livre.getPageDepart()))){
            return false;
         }
        //verification de derniere page = Page sortie        
        if(!(pages.get(pages.size()-1).equals(livre.getPageSortie()))){
            return false;
         }
         // fonction estChaine du Tp1
         for(int i = 0; i < pages.size()-1; i++){
            if (!(livre.getGraphe().containsEdge(pages.get(i), pages.get(i+1)))) {
                return false;
            }
         } 
         // verification objet collecté est l'objet requis
         Set<Object> objetsCollecté = new HashSet<Object>();
         for(Page p : pages){
            objetsCollecté.addAll(p.getObjets());
         }
         return objetsCollecté.equals(livre.getObjetsRequis());
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