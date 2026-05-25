package com.livrejeu;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class LivreJeu {
    private Graph<Page, DefaultWeightedEdge> graphe;
    private Page pageDepart;
    private Page pageSortie;
    private Set<Objet> objetsRequis;
    private Map<Integer, Page> pages;

    public LivreJeu() {
        this.graphe = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        this.objetsRequis = new HashSet<>();
        this.pages = new HashMap<>();
        this.pageDepart = null;
        this.pageSortie = null;
    }

    public void ajouterPage(Page p) {
        this.pages.put(p.getId(), p);
        this.graphe.addVertex(p);
    }

    public void ajouterLien(Page src, Page dst) {
        DefaultWeightedEdge edge = this.graphe.addEdge(src, dst);
        if (edge != null && src.getEnigme() != null) {
            this.graphe.setEdgeWeight(edge, src.getEnigme().getTempsResolution());
        }
    }

    public Page getPage(int id) {
        return this.pages.get(id);
    }

    public Page getPageDepart() {
        return this.pageDepart;
    }

    public void setPageDepart(Page p) {
        this.pageDepart = p;
    }

    public Page getPageSortie() {
        return this.pageSortie;
    }

    public void setPageSortie(Page p) {
        this.pageSortie = p;
    }

    public Set<Objet> getObjetsRequis() {
        return this.objetsRequis;
    }

    public void setObjetsRequis(Set<Objet> objets) {
        this.objetsRequis = objets;
    }

    public Graph<Page, DefaultWeightedEdge> getGraphe() {
        return this.graphe;
    }

    public int getNbPages() {
        return this.pages.size();
    }

    @Override
    public String toString() {
        return "LivreJeu{pages=" + pages.size() +
                ", depart=" + pageDepart +
                ", sortie=" + pageSortie + "}";
    }
}
