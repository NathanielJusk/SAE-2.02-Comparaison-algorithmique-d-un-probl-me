package com.livrejeu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LivreJeuTest {

    @Test
    void testGetPageDepart() {
        LivreJeu lj = new LivreJeu(1, 10);
        assertEquals(1, lj.getPageDepart());
    }

    @Test
    void testGetPageSortie() {
        LivreJeu lj = new LivreJeu(1, 10);
        assertEquals(10, lj.getPageSortie());
    }

    @Test
    void testAjouterPage() {
        LivreJeu lj = new LivreJeu(1, 10);
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(1, e);
        lj.ajouterPage(p);
        assertTrue(lj.getPages().contains(p));
    }

    @Test
    void testAjouterLien() {
        LivreJeu lj = new LivreJeu(1, 10);
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(0, e);
        lj.ajouterPage(p);
        lj.ajouterLien(0, 2);
        assertTrue(lj.getVoisins(0).contains(2));
    }

    @Test
    void testObjetsRequisVide() {
        LivreJeu lj = new LivreJeu(1, 10);
        assertTrue(lj.getObjetsRequis().isEmpty());
    }

    @Test
    void testAjouterObjetRequis() {
        LivreJeu lj = new LivreJeu(1, 10);
        Objet o = new Objet("o1");
        lj.ajouterObjetRequis(o);
        assertTrue(lj.getObjetsRequis().contains(o));
    }
}