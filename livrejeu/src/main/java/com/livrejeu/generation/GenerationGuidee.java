package com.livrejeu.generation;

import com.livrejeu.LivreJeu;
import com.livrejeu.Page;

import java.util.List;

public class GenerationGuidee implements AlgorithmeGeneration {

    @Override
    public LivreJeu generer(int nbPages, int numDepart, int numSortie, int nbObjets) {
        // TODO
        // 1. Créer un chemin principal garanti de depart à sortie
        // 2. Ajouter des pages secondaires avec liens
        // 3. Appeler garantirAccessibilite()
        // 4. Placer les objets sur le chemin principal
        return null;
    }

    private void garantirAccessibilite(LivreJeu livre) {
        // TODO
        // Vérifier via BFS/DFS que toutes les pages sont atteignables
        // Ajouter des liens manquants si nécessaire
    }
}
