package com.livrejeu;

import java.util.ArrayList;
import java.util.List;

public class LivreJeu {
    private List<Page> pages;
    private List<List<Integer>> adjacence;
    private int pageDepart;
    private int pageSortie;
    private List<Objet> objetsRequis;

    public LivreJeu(int pageDepart, int pageSortie) {
        this.pageDepart = pageDepart;
        this.pageSortie = pageSortie;
        this.pages = new ArrayList<>();
        this.adjacence = new ArrayList<>();
        this.objetsRequis = new ArrayList<>();
    }

    public int getPageDepart() {
        return pageDepart;
    }

    public int getPageSortie() {
        return pageSortie;
    }

    public List<Page> getPages() {
        return pages;
    }

    public List<List<Integer>> getAdjacence() {
        return adjacence;
    }

    public List<Objet> getObjetsRequis() {
        return objetsRequis;
    }

    public void ajouterPage(Page p) {
        pages.add(p);
        adjacence.add(new ArrayList<>());
    }

    public void ajouterLien(int de, int vers) {
        adjacence.get(de).add(vers);
    }

    public List<Integer> getVoisins(int id) {
        return adjacence.get(id);
    }

    public void ajouterObjetRequis(Objet o) {
        objetsRequis.add(o);
    }

}
