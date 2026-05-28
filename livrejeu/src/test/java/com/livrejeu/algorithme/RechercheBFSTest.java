package com.livrejeu.algorithme;

import com.livrejeu.Enigme;
import com.livrejeu.LivreJeu;
import com.livrejeu.Objet;
import com.livrejeu.Page;
import com.livrejeu.Solution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

public class RechercheBFSTest {

    private LivreJeu livre;
    private Page p1, p2, p3, p4;
    private Objet cle, torche;

    @BeforeEach
    void setUp() {
        cle    = new Objet("cle");
        torche = new Objet("torche");

        p1 = new Page(1, new Enigme("Depart", 1),  new HashSet<>(Arrays.asList(cle)));
        p2 = new Page(2, new Enigme("Page 2", 10), new HashSet<>());
        p3 = new Page(3, new Enigme("Page 3", 2),  new HashSet<>(Arrays.asList(torche)));
        p4 = new Page(4, new Enigme("Sortie", 0),  new HashSet<>());

        livre = new LivreJeu();
        livre.ajouterPage(p1);
        livre.ajouterPage(p2);
        livre.ajouterPage(p3);
        livre.ajouterPage(p4);

        livre.ajouterLien(p1, p2);
        livre.ajouterLien(p2, p4);
        livre.ajouterLien(p1, p3);
        livre.ajouterLien(p3, p4);

        livre.setPageDepart(p1);
        livre.setPageSortie(p4);
        livre.setObjetsRequis(new HashSet<>(Arrays.asList(cle, torche)));
    }

    @Test
    void bfsTrouveSolution() {
        Solution sol = new AlgorithmeBFS().resoudre(livre);
        assertNotNull(sol);
    }

    @Test
    void bfsSolutionValide() {
        Solution sol = new AlgorithmeBFS().resoudre(livre);
        assertNotNull(sol);
        assertTrue(sol.estValide(livre));
    }

    @Test
    void bfsTrouveLePlusCourt() {
        Solution sol = new AlgorithmeBFS().resoudre(livre);
        assertNotNull(sol);
        assertEquals(2, sol.getLongueur());
    }

    @Test
    void pasDeSolutionSiPasDeLien() {
        LivreJeu livreVide = new LivreJeu();
        livreVide.ajouterPage(p1);
        livreVide.ajouterPage(p4);
        livreVide.setPageDepart(p1);
        livreVide.setPageSortie(p4);
        livreVide.setObjetsRequis(new HashSet<>());
        assertNull(new AlgorithmeBFS().resoudre(livreVide));
    }

    @Test
    void bfsAvecUnSeulChemin() {
        LivreJeu livreSimple = new LivreJeu();
        livreSimple.ajouterPage(p1);
        livreSimple.ajouterPage(p4);
        livreSimple.ajouterLien(p1, p4);
        livreSimple.setPageDepart(p1);
        livreSimple.setPageSortie(p4);
        livreSimple.setObjetsRequis(new HashSet<>());
        assertNotNull(new AlgorithmeBFS().resoudre(livreSimple));
    }
}
