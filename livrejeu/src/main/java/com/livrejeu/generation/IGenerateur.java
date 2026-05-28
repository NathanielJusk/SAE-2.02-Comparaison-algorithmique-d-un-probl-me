package com.livrejeu.generation;

import com.livrejeu.LivreJeu;
import com.livrejeu.Objet;


import java.util.Map;
import java.util.Set;

public interface IGenerateur {
    LivreJeu generer(int nbPages, int idDepart, int idSortie, Map<Integer, Set<Objet>> objets);
}
