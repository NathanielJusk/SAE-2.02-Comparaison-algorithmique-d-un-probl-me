package com.livrejeu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnigmeTest {

    @Test
    void testGetTexte() {
        Enigme e = new Enigme("Quelle est la couleur du cheval blanc ?", 5);
        assertEquals("Quelle est la couleur du cheval blanc ?", e.getTexte());
    }

    @Test
    void testGetTempsResolution() {
        Enigme e = new Enigme("Quelle est la couleur du cheval blanc ?", 5);
        assertEquals(5, e.getTempsResolution());
    }
}