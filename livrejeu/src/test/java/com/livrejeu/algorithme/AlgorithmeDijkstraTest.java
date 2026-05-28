package com.livrejeu.algorithme;

import com.livrejeu.Enigme;
import com.livrejeu.LivreJeu;
import com.livrejeu.Objet;
import com.livrejeu.Page;
import com.livrejeu.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class AlgorithmeDijkstraTest {

    @Test
    void testCheminSimple() {
        LivreJeu livre = new LivreJeu();

        Page p1 = new Page(1, new Enigme("Debut", 5), new HashSet<>());
        Page p2 = new Page(2, new Enigme("Fin", 3), new HashSet<>());

        livre.ajouterPage(p1);
        livre.ajouterPage(p2);
        livre.ajouterLien(p1, p2);
        livre.setPageDepart(p1);
        livre.setPageSortie(p2);

        Solution sol = new AlgorithmeDijkstra().resoudre(livre);

        assertNotNull(sol);
        assertEquals(2, sol.getPages().size());
    }

    // ── Test 2 : aucun chemin possible
    @Test
    void testAucunChemin() {
        LivreJeu livre = new LivreJeu();

        Page p1 = new Page(1, new Enigme("Debut", 5), new HashSet<>());
        Page p2 = new Page(2, new Enigme("Fin", 3), new HashSet<>());

        livre.ajouterPage(p1);
        livre.ajouterPage(p2);
        // pas de lien
        livre.setPageDepart(p1);
        livre.setPageSortie(p2);

        Solution sol = new AlgorithmeDijkstra().resoudre(livre);

        assertNull(sol);
    }


    @Test
    void testMeilleurChemin() {
        LivreJeu livre = new LivreJeu();

        Page p1 = new Page(1, new Enigme("Debut", 10), new HashSet<>());
        Page p2 = new Page(2, new Enigme("Chemin long", 10), new HashSet<>());
        Page p3 = new Page(3, new Enigme("Chemin court", 1), new HashSet<>());
        Page p4 = new Page(4, new Enigme("Fin", 1), new HashSet<>());

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

        Solution sol = new AlgorithmeDijkstra().resoudre(livre);

        assertNotNull(sol);
        assertEquals(p3, sol.getPages().get(1));
    }

    @Test
    void testObjetManquant() {
        LivreJeu livre = new LivreJeu();

        Page p1 = new Page(1, new Enigme("Debut", 5), new HashSet<>());
        Page p2 = new Page(2, new Enigme("Fin", 3), new HashSet<>());

        livre.ajouterPage(p1);
        livre.ajouterPage(p2);
        livre.ajouterLien(p1, p2);
        livre.setPageDepart(p1);
        livre.setPageSortie(p2);

        Set<Objet> objetsRequis = new HashSet<>();
        objetsRequis.add(new Objet("cle"));
        livre.setObjetsRequis(objetsRequis);

        Solution sol = new AlgorithmeDijkstra().resoudre(livre);

        assertNull(sol);
    }

    @Test
    void testObjetPresent() {
        LivreJeu livre = new LivreJeu();

        Set<Objet> objetsP1 = new HashSet<>();
        objetsP1.add(new Objet("cle"));

        Page p1 = new Page(1, new Enigme("Debut", 5), objetsP1);
        Page p2 = new Page(2, new Enigme("Fin", 3), new HashSet<>());

        livre.ajouterPage(p1);
        livre.ajouterPage(p2);
        livre.ajouterLien(p1, p2);
        livre.setPageDepart(p1);
        livre.setPageSortie(p2);

        Set<Objet> objetsRequis = new HashSet<>();
        objetsRequis.add(new Objet("cle"));
        livre.setObjetsRequis(objetsRequis);

        Solution sol = new AlgorithmeDijkstra().resoudre(livre);

        assertNotNull(sol);
    }
}