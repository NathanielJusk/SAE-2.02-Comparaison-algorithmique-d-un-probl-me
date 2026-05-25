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

    private static class FilePriorite {
        private List<Page> liste = new ArrayList<>();
        private Map<Page, Integer> cout;

        public FilePriorite(Map<Page, Integer> cout) {
            this.cout = cout;
        }

        public void ajouter(Page p) {
            liste.add(p);
        }

        public void supprimer(Page p) {
            liste.remove(p);
        }

        public boolean estVide() {
            return liste.isEmpty();
        }

        public Page retirerMinimum() {
            Page min = liste.get(0);
            for (Page p : liste) {
                if (cout.get(p) < cout.get(min)) {
                    min = p;
                }
            }
            liste.remove(min);
            return min;
        }
    }

    @Override
    public Solution resoudre(LivreJeu livre) {
        long debut = System.currentTimeMillis();

        Graph<Page, DefaultWeightedEdge> graphe = livre.getGraphe();
        Page source = livre.getPageDepart();
        Page sortie = livre.getPageSortie();

        Map<Page, Integer> cout = initialisationDuCout(graphe, source);
        
        Map<Page, Page> predecesseur = initialisationPredecesseurs(graphe);

        djikstra(graphe, cout, predecesseur);

        if (cout.get(sortie) == Integer.MAX_VALUE) return null;

        List<Page> chemin = reconstruireChemin(predecesseur, sortie);

        if (!objetsValides(chemin, livre.getObjetsRequis())) return null;

        long fin = System.currentTimeMillis();
        return new Solution(chemin, fin - debut);
    }
    

    // On initialise tout les couts à + l'infini hors mi
    // la page source qui est initialisé à 0
    private Map<Page, Integer> initialisationDuCout(Graph<Page, DefaultWeightedEdge> graphe, Page source) {
        Map<Page, Integer> cout = new HashMap<>();
        for (Page p : graphe.vertexSet()) {
            cout.put(p, Integer.MAX_VALUE);
        }
        cout.put(source, 0);
        return cout;
    }

    // initialiser tout les predecesseur à null
    private Map<Page, Page> initialisationPredecesseurs(Graph<Page, DefaultWeightedEdge> graphe) {
        Map<Page, Page> predecesseurs = new HashMap<>();
        for (Page p : graphe.vertexSet()) {
            predecesseurs.put(p, null);
        }
        return predecesseurs;
    }

    private void djikstra(Graph<Page, DefaultWeightedEdge> graphe,
            Map<Page, Integer> cout,
            Map<Page, Page> predecesseurs) {
        Set<Page> marques = new HashSet<>();
        FilePriorite Q = new FilePriorite(cout);
        for (Page p : graphe.vertexSet()) {
            Q.ajouter(p);
        }
        while (!Q.estVide()) {
            Page vmin = Q.retirerMinimum();
            marques.add(vmin);
            relaxer(graphe, vmin, cout, predecesseurs, marques, Q);
        }
    }
    // https://jgrapht.org/javadoc-1.0.0/org/jgrapht/DirectedGraph.html
    // outgoingEdgesOf() renvoie l'ensemble des aretes
    // Paramètres :
    // vertex- le sommet pour lequel la liste des arêtes sortantes doit être
    // renvoyée.
    // Retours :
    // l'ensemble de toutes les arêtes partant du sommet spécifié.

    private void relaxer(Graph<Page, DefaultWeightedEdge> graphe,
                            Page vmin,
                            Map<Page, Integer> cout,
                            Map<Page, Page> predecesseur,
                            Set<Page> marques,
                            FilePriorite Q
      )  {
        for( DefaultWeightedEdge e: graphe.outgoingEdgesOf(vmin)){
            Page voisin = graphe.getEdgeTarget(e);
            if(marques.contains(voisin)){
                continue;
            }
            int nouveauCout = cout.get(vmin) + (int) graphe.getEdgeWeight(e);
            if(nouveauCout < cout.get(voisin)){
                cout.put(voisin, nouveauCout);
                predecesseur.put(voisin, vmin);
                Q.supprimer(voisin);
                Q.ajouter(voisin);
            }
        }


      }
  private List<Page> reconstruireChemin(Map<Page, Page> predecesseur, Page sortie) {
        List<Page> chemin = new ArrayList<>();
        Page courant = sortie;
        while (courant != null) {
            chemin.add(0, courant);
            courant = predecesseur.get(courant);
        }
        return chemin;
    }
    private boolean objetsValides(List<Page> chemin, Set<Objet> objetsRequis) {
        Set<Objet> objetsCollectes = new HashSet<>();
        for (Page p : chemin){
            objetsCollectes.addAll(p.getObjets());
        } 
        return objetsCollectes.containsAll(objetsRequis);
    }
}

