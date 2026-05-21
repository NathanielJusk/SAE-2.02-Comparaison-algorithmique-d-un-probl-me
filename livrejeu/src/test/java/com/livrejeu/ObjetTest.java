package com.livrejeu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObjetTest {

    @Test
    void testGetNom() {
        Objet o = new Objet("o1");
        assertEquals("o1", o.getNom());
    }


}