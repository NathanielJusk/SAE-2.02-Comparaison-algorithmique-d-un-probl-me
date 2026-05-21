package com.livrejeu.generation;

import com.livrejeu.LivreJeu;
import com.livrejeu.Page;

import java.util.List;

public class GenerationAleatoire implements AlgorithmeGeneration {

    @Override
    public LivreJeu generer(int nbPages, int numDepart, int numSortie, int nbObjets) {
        // TODO
        // 1. Créer nbPages pages avec enigmes générées aléatoirement
        // 2. Appeler creerLiensAleatoires()
        // 3. Placer les objets sur des pages aléatoires
        // 4. Retourner le LivreJeu
        return null;
    }

    private void creerLiensAleatoires(List<Page> pages, Page depart, Page sortie) {
        // TODO
        // Garantir :
        // - toute page atteignable depuis depart (BFS/DFS vérif)
        // - sortie accessible depuis toute page
        // Utiliser Random pour créer des liens variés
    }
}
