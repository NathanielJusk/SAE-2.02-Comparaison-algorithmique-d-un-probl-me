package com.livrejeu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

public class PageTest {

    @Test
    void testGetId() {
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(1, e, new HashSet<>());
        assertEquals(1, p.getId());
    }

    @Test
    void testGetEnigme() {
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(1, e, new HashSet<>());
        assertEquals(e, p.getEnigme());
    }

    @Test
    void testGetObjetsVide() {
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(1, e, new HashSet<>());
        assertTrue(p.getObjets().isEmpty());
    }

    @Test
    void testAjouterObjet() {
        Enigme e = new Enigme("Une énigme", 3);
        Page p = new Page(1, e, new HashSet<>());
        Objet o = new Objet("o1");
        p.getObjets().add(o);
        assertTrue(p.getObjets().contains(o));
    }
}