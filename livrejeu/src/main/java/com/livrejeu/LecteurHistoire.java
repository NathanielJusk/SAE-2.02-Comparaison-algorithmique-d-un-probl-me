package com.livrejeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LecteurHistoire {

    public static List<Page> charger(String cheminFichier) throws IOException {
        List<Page> pages = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));

        // Sauter l'en-tete
        String ligne = reader.readLine();

        int id = 1;
        ligne = reader.readLine();
        
        while (ligne != null) {
            // on separe les elements
            String[] col = ligne.split(",");
            
            String texte = col[0];
            int temps = Integer.parseInt(col[1]);

            Set<Objet> objets = new HashSet<>();
            
            // gestion basique de la presence d'objet ou non
            if (col.length == 3) {
                objets.add(new Objet(col[2]));
            }

            Page p = new Page(id, new Enigme(texte, temps), objets);
            pages.add(p);
            
            id++;
            ligne = reader.readLine();
        }

        reader.close();
        return pages;
    }
}