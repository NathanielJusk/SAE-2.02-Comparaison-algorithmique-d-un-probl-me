package com.livrejeu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObjetTest {

    @Test
    void testGetId() {
        Objet o = new Objet("o1", "Clé");
        assertEquals("o1", o.getId());
    }

    @Test
    void testGetNom() {
        Objet o = new Objet("o1", "Clé");
        assertEquals("Clé", o.getNom());
    }
}