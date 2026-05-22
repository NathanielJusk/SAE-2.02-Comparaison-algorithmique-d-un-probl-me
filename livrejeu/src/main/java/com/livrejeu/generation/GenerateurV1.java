package com.livrejeu.generation;

import com.livrejeu.LivreJeu;
import com.livrejeu.Page;
import com.livrejeu.Solution;
import com.livrejeu.Objet;


import java.util.List;

import java.util.Map;
import java.util.Set;

public class GenerateurV1 implements IGenerateur {
    @Override

    public LivreJeu generer(int nbPages, int idDepart, int idSortie, Map<Integer, Set<Objet>> objets) {
        return new LivreJeu();
    }
}