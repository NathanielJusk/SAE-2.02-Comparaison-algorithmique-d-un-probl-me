package com.livrejeu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

public class LivreJeuTest {

    @Test
    void testGetPageDepart() {
        LivreJeu lj = new LivreJeu();
        Page p1 = new Page(1, null, new HashSet<>());
        lj.setPageDepart(p1);
        assertEquals(p1, lj.getPageDepart());
    }

    @Test
    void testGetPageSortie() {
        LivreJeu lj = new LivreJeu();
        Page p10 = new Page(10, null, new HashSet<>());
        lj.setPageSortie(p10);
        assertEquals(p10, lj.getPageSortie());
    }

    @Test
    void testAjouterPage() {
        LivreJeu lj = new LivreJeu();
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(1, e, new HashSet<>());
        lj.ajouterPage(p);
        assertEquals(p, lj.getPage(1));
    }

    @Test
    void testAjouterLien() {
        LivreJeu lj = new LivreJeu();
        Enigme e = new Enigme("Une énigme", 3);
        Page p1 = new Page(0, e, new HashSet<>());
        Page p2 = new Page(2, null, new HashSet<>());
        lj.ajouterPage(p1);
        lj.ajouterPage(p2);
        lj.ajouterLien(p1, p2);
        assertTrue(lj.getGraphe().containsEdge(p1, p2));
    }

    @Test
    void testObjetsRequisVide() {
        LivreJeu lj = new LivreJeu();
        assertTrue(lj.getObjetsRequis().isEmpty());
    }

    @Test
    void testAjouterObjetRequis() {
        LivreJeu lj = new LivreJeu();
        Objet o = new Objet("o1");
        lj.getObjetsRequis().add(o);
        assertTrue(lj.getObjetsRequis().contains(o));
    }
}