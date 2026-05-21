package com.livrejeu.algorithme;

import com.livrejeu.LivreJeu;
import com.livrejeu.Objet;
import com.livrejeu.Page;
import com.livrejeu.Solution;

import java.util.List;
import java.util.Set;

public class RechercheDFS implements AlgorithmeRecherche {

    @Override
    public Solution resoudre(LivreJeu livre) {
        // TODO
        // 1. Initialiser chemin, visites, objetsCollectes
        // 2. Appeler explorer() depuis pageDepart
        // 3. Retourner la solution ou null si pas trouvé
        return null;
    }

    private boolean explorer(Page courante, Page sortie,
                              Set<Objet> objetsRequis,
                              Set<Page> visites,
                              List<Page> chemin,
                              Set<Objet> objetsCollectes) {
        // TODO
        // 1. Marquer courante visitée, ajouter au chemin
        // 2. Collecter les objets de courante
        // 3. Si courante == sortie ET tous objets OK → return true
        // 4. Explorer récursivement les pagesSuivantes non visitées
        // 5. Backtrack si aucun chemin trouvé
        return false;
    }
}
