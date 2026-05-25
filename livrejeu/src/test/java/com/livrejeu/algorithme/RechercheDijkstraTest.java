package com.livrejeu.algorithme;

import com.livrejeu.Enigme;
import com.livrejeu.LivreJeu;
import com.livrejeu.Objet;
import com.livrejeu.Page;
import com.livrejeu.Solution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class RechercheDijkstraTest {

    private LivreJeu livre;
    private Page p1, p2, p3, p4;
    private Objet cle, torche;

    @BeforeEach
    void setUp() {
        cle = new Objet("clé");
        torche = new Objet("torche");

        // Page 1 (départ) contient la clé — énigme de 3 sec
        p1 = new Page(1, new Enigme("Trouve la clé", 3), new HashSet<>(Arrays.asList(cle)));
        // Page 2 — énigme de 7 sec, pas d'objet
        p2 = new Page(2, new Enigme("Déchiffre le code", 7), new HashSet<>());
        // Page 3 — contient la torche, énigme de 5 sec
        p3 = new Page(3, new Enigme("Traverse le pont", 5), new HashSet<>(Arrays.asList(torche)));
        // Page 4 (sortie) — pas d'objet
        p4 = new Page(4, new Enigme("Sortie !", 0), new HashSet<>());

        livre = new LivreJeu();
        livre.ajouterPage(p1);
        livre.ajouterPage(p2);
        livre.ajouterPage(p3);
        livre.ajouterPage(p4);

        // Liens : 1→2→4 et 1→3→4
        livre.ajouterLien(p1, p2);
        livre.ajouterLien(p1, p3);
        livre.ajouterLien(p2, p4);
        livre.ajouterLien(p3, p4);

        livre.setPageDepart(p1);
        livre.setPageSortie(p4);
        livre.setObjetsRequis(new HashSet<>(Arrays.asList(cle, torche)));
    }

    @Test
    void trouveSolutionSimple() {
        Solution sol = new AlgorithmeDijkstra().resoudre(livre);
        assertNotNull(sol); // une solution doit exister
    }

    @Test
    void retourneNullSiPasDeSolution() {
        // On supprime tous les liens → plus de chemin possible
        LivreJeu livreVide = new LivreJeu();
        livreVide.ajouterPage(p1);
        livreVide.ajouterPage(p4);
        livreVide.setPageDepart(p1);
        livreVide.setPageSortie(p4);
        livreVide.setObjetsRequis(new HashSet<>());
        // Aucun lien entre p1 et p4
        Solution sol = new AlgorithmeDijkstra().resoudre(livreVide);
        assertNull(sol); // pas de solution
    }

    @Test
    void solutionEstValide() {
        Solution sol = new AlgorithmeDijkstra().resoudre(livre);
        assertNotNull(sol);
        assertTrue(sol.estValide(livre)); // solution valide selon les règles du sujet
    }

    @Test
    void solutionCommenceAuDepart() {
        Solution sol = new AlgorithmeDijkstra().resoudre(livre);
        assertNotNull(sol);
        assertEquals(p1, sol.getPages().get(0)); // première page = départ
    }

    @Test
    void solutionTermineAlaSortie() {
        Solution sol = new AlgorithmeDijkstra().resoudre(livre);
        assertNotNull(sol);
        assertEquals(p4, sol.getPages().get(sol.getLongueur() - 1)); // dernière page = sortie
    }

    @Test
    void solutionCollecteTousLesObjets() {
        Solution sol = new AlgorithmeDijkstra().resoudre(livre);
        assertNotNull(sol);
        // Vérifier que clé et torche sont collectées
        assertTrue(sol.estValide(livre));
    }
}