package com.livrejeu.algorithme;

import com.livrejeu.LivreJeu;
import com.livrejeu.Objet;
import com.livrejeu.Page;
import com.livrejeu.Solution;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;

public class AlgorithmeBFS implements AlgorithmeRecherche {

    @Override
    public Solution resoudre(LivreJeu livre) {
        
        long debut = System.currentTimeMillis();
        Graph<Page, DefaultWeightedEdge> graphe = livre.getGraphe();
        Page source = livre.getPageDepart();
        Page sortie = livre.getPageSortie();
        Set<Objet> objetsRequis = livre.getObjetsRequis();
        Set<String> visites = new HashSet<>(); 

        Queue<List<Page>> file = new LinkedList<>();
        List<Page> cheminInitial = new ArrayList<>();
        cheminInitial.add(source);
        file.add(cheminInitial);

        visites.add(source.getId()+"-"+objetsCollectes(cheminInitial).toString());

        while (!file.isEmpty()) {
            List<Page> cheminCourant = file.poll();
            Page courant = cheminCourant.get(cheminCourant.size() - 1);

            if (courant.equals(sortie)) {
                if (objetsCollectes(cheminCourant).containsAll(objetsRequis)) {
                    long fin = System.currentTimeMillis();
                    return new Solution(cheminCourant, fin - debut);
                }
            }

            for (DefaultWeightedEdge e : graphe.outgoingEdgesOf(courant)) {
                Page voisin = graphe.getEdgeTarget(e);
                
                List<Page> nouveauChemin = new ArrayList<>(cheminCourant);
                nouveauChemin.add(voisin);
                String etat = voisin.getId()+"-"+ objetsCollectes(nouveauChemin).toString();
                
                if (!visites.contains(etat)) {
                    visites.add(etat);
                    file.add(nouveauChemin);
                }
            }
        }

        return null;
    }

    private Set<Objet> objetsCollectes(List<Page> chemin) {
        Set<Objet> objets = new HashSet<>();
        for (Page p : chemin) {
            objets.addAll(p.getObjets());
        }
        return objets;
    }
}
