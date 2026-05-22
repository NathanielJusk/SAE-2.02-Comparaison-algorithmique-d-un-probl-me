package com.livrejeu;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.nio.csv.CSVImporter;
import org.jgrapht.nio.dot.DOTExporter;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.util.SupplierUtil;

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
    }

    public void ajouterPage(Page p) {
        this.pages.put(p.getId(), p);
        this.graphe.addVertex(p);
    }

    public void ajouterLien(Page src, Page dst) {
        this.graphe.addEdge(src, dst);
        if (src.getEnigme() != null) {
            this.graphe.setEdgeWeight(src, dst, src.getEnigme().getTempsResolution());
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
}
