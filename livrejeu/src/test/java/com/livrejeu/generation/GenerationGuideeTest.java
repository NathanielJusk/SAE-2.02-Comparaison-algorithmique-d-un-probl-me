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
        
        assertNotNull(livre, "Le livre généré par la génération guidée ne doit pas être null.");
        assertEquals(8, livre.getNbPages(), "Le nombre de pages générées doit être respecté.");
        assertNotNull(livre.getPageDepart());
        assertNotNull(livre.getPageSortie());
    }

    @Test
    void solutionExisteToujoursAvecGenerationGuidee() {
        IGenerateur generateur = new GenerateurV2();
        LivreJeu livre = generateur.generer(15, 1, 15, new HashMap<>());
        
        AlgorithmeBFS algo = new AlgorithmeBFS();
        Solution solution = algo.resoudre(livre);
        
        assertNotNull(solution, "La génération guidée doit toujours garantir l'existence d'une solution.");
        assertTrue(solution.estValide(livre), "La solution trouvée doit être un chemin valide selon les règles du graphe.");
    }
}