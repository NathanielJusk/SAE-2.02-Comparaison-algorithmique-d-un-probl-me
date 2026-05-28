package com.livrejeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SolutionTest {

    private LivreJeu livre;
    private Page page1, page2, page3;

    @BeforeEach
    void setUp() {
        livre = new LivreJeu();
        
        page1 = new Page(1, null, new HashSet<>());
        page2 = new Page(2, null, new HashSet<>());
        page3 = new Page(3, null, new HashSet<>());
        
        livre.ajouterPage(page1);
        livre.ajouterPage(page2);
        livre.ajouterPage(page3);
        
        livre.setPageDepart(page1);
        livre.setPageSortie(page3);
        
        livre.ajouterLien(page1, page2);
        livre.ajouterLien(page2, page3);
    }

    @Test
    void solutionValideSimple() {
        List<Page> chemin = new ArrayList<>();
        chemin.add(page1);
        chemin.add(page2);
        chemin.add(page3);
        
        Solution solution = new Solution(chemin, 100);
        assertTrue(solution.estValide(livre));
    }

    @Test
    void solutionInvalide_mauvaisePageDepart() {
        List<Page> chemin = new ArrayList<>();
        chemin.add(page2);
        chemin.add(page3);
        
        Solution solution = new Solution(chemin, 100);
        assertFalse(solution.estValide(livre));
    }

    @Test
    void solutionInvalide_lienManquant() {
        List<Page> chemin = new ArrayList<>();
        chemin.add(page1);
        chemin.add(page3); // Pas de lien direct entre 1 et 3
        
        Solution solution = new Solution(chemin, 100);
        assertFalse(solution.estValide(livre));
    }

    @Test
    void longueurCorrecte() {
        List<Page> chemin = new ArrayList<>();
        chemin.add(page1);
        chemin.add(page2);
        chemin.add(page3);
        
        Solution solution = new Solution(chemin, 100);
        // 3 pages = 2 liens traversés
        assertEquals(2, solution.getLongueur()); 
    }

    @Test
    void tempsTotalCorrect() {
        List<Page> chemin = new ArrayList<>();
        chemin.add(page1);
        chemin.add(page2);
        
        Solution solution = new Solution(chemin, 150);
        assertEquals(150, solution.getTempsExecution());
    }
}
