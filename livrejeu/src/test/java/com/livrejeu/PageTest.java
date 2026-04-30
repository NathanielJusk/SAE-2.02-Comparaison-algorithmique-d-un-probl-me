package com.livrejeu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PageTest {

    @Test
    void testGetId() {
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(1, e);
        assertEquals(1, p.getId());
    }

    @Test
    void testGetEnigme() {
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(1, e);
        assertEquals(e, p.getEnigme());
    }

    @Test
    void testGetObjetsVide() {
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(1, e);
        assertTrue(p.getObjets().isEmpty());
    }

    @Test
    void testAjouterObjet() {
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(1, e);
        Objet o = new Objet("o1", "Clé");
        p.ajouterObjet(o);
        assertTrue(p.getObjets().contains(o));
    }
}