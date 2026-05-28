package com.livrejeu.generation;

import com.livrejeu.LivreJeu;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class GenerationAleatoireTest {

    @Test
    void livreGenereEstValide() {
        IGenerateur generateur = new GenerateurV1();
        LivreJeu livre = generateur.generer(5, 1, 5, new HashMap<>());
        
        assertNotNull(livre);
        assertNotNull(livre.getPageDepart());
        assertNotNull(livre.getPageSortie());
        assertEquals(1, livre.getPageDepart().getId());
        assertEquals(5, livre.getPageSortie().getId());
    }

    @Test
    void livreALeNombreDePagesDemande() {
        IGenerateur generateur = new GenerateurV1();
        LivreJeu livre = generateur.generer(10, 1, 10, new HashMap<>());
        
        assertEquals(10, livre.getNbPages());
    }
}