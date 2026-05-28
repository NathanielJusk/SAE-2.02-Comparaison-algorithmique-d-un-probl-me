package com.livrejeu.algorithme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import com.livrejeu.LivreJeu;
import com.livrejeu.Objet;
import com.livrejeu.Page;
import com.livrejeu.Solution;

public class AlgorithmeDijkstra implements AlgorithmeRecherche {

    @Override
    public Solution resoudre(LivreJeu livre) {
        long debut = System.currentTimeMillis();

        Graph<Page, DefaultWeightedEdge> graphe = livre.getGraphe();
        Page source = livre.getPageDepart();
        Page sortie = livre.getPageSortie();

        // Initialisation 
        Map<Page, Integer> cout = new HashMap<>();
        Map<Page, Page> predecesseur = new HashMap<>();
        List<Page> aTraiter = new ArrayList<>();
        Set<Page> marques = new HashSet<>();

        for (Page p : graphe.vertexSet()) {
            cout.put(p, Integer.MAX_VALUE);
            predecesseur.put(p, null);
            aTraiter.add(p);
        }
        cout.put(source, 0);

        // Boucle principale
        while (!aTraiter.isEmpty()) {
            // Cherche le sommet avec le plus petit cout
            Page vmin = null;
            int min = Integer.MAX_VALUE;
            for (Page p : aTraiter) {
                if (cout.get(p) <= min) {
                    min = cout.get(p);
                    vmin = p;
                }
            }

            if (vmin == null || cout.get(vmin) == Integer.MAX_VALUE) {
                break; // Plus rien d'atteignable
            }

            aTraiter.remove(vmin);
            marques.add(vmin);

            // on recupere les aretes sortantes avec la doc jgrapht
            for (DefaultWeightedEdge e : graphe.outgoingEdgesOf(vmin)) {
                Page voisin = graphe.getEdgeTarget(e);
                
                if (marques.contains(voisin)) {
                    continue;
                }

                int nouveauCout = cout.get(vmin) + (int) graphe.getEdgeWeight(e);
                if (nouveauCout < cout.get(voisin)) {
                    cout.put(voisin, nouveauCout);
                    predecesseur.put(voisin, vmin);
                }
            }
        }

        if (cout.get(sortie) == Integer.MAX_VALUE) {
            return null;
        }
            
        // Reconstruire le chemin a l'envers
        List<Page> chemin = new ArrayList<>();
        Page courant = sortie;
        while (courant != null) {
            chemin.add(0, courant);
            courant = predecesseur.get(courant);
        }

        if (!objetsValides(chemin, livre.getObjetsRequis())) {
           return null; 
        } 

        long fin = System.currentTimeMillis();
        return new Solution(chemin, fin - debut);
    }
    private boolean objetsValides(List<Page> chemin, Set<Objet> objetsRequis) {
        Set<Objet> objetsCollectes = new HashSet<>();
        for (Page p : chemin){
            objetsCollectes.addAll(p.getObjets());
        } 
        return objetsCollectes.containsAll(objetsRequis);
    }
}

