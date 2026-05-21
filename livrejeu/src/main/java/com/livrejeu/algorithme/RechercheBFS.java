package com.livrejeu.algorithme;

import com.livrejeu.LivreJeu;
import com.livrejeu.Objet;
import com.livrejeu.Page;
import com.livrejeu.Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RechercheBFS implements AlgorithmeRecherche {

    private static class Etat {

        Page page;
        List<Page> chemin;
        Set<Objet> objetsCollectes;

        Etat(Page page, List<Page> chemin, Set<Objet> objetsCollectes) {
            // TODO
        }
    }

    @Override
    public Solution resoudre(LivreJeu livre) {
        // TODO
        // 1. Initialiser la file avec l'état initial (pageDepart)
        // 2. Tant que file non vide :
        //    - Dépiler un état
        //    - Si page == sortie ET objets OK → construire et retourner Solution
        //    - Sinon ajouter les voisins non visités dans la file
        // 3. Retourner null si pas de solution
        return null;
    }
}
