package com.livrejeu.generation;

import com.livrejeu.LivreJeu;
import com.livrejeu.Solution;
import com.livrejeu.algorithme.AlgorithmeBFS;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class GenerationGuideeTest {

    @Test
    void livreGenereEstValide() {
        IGenerateur generateur = new GenerateurV2();
        LivreJeu livre = generateur.generer(8, 1, 8, new HashMap<>());
        
        assertNotNull(livre);
        assertEquals(8, livre.getNbPages());
        assertNotNull(livre.getPageDepart());
        assertNotNull(livre.getPageSortie());
    }

    @Test
    void solutionExisteToujoursAvecGenerationGuidee() {
        IGenerateur generateur = new GenerateurV2();
        LivreJeu livre = generateur.generer(15, 1, 15, new HashMap<>());
        
        AlgorithmeBFS algo = new AlgorithmeBFS(); 
        Solution solution = algo.resoudre(livre);
        
        assertNotNull(solution);
        assertTrue(solution.estValide(livre));
    }
}