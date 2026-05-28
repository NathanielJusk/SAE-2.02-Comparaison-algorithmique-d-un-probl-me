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
        reader.readLine();

        String ligne;
        int id = 1;

        while ((ligne = reader.readLine()) != null) {
            if (ligne.trim().isEmpty()) continue;

            String[] col = ligne.split(",");
            String texte = col[0].trim();
            int temps    = Integer.parseInt(col[1].trim());

            Set<Objet> objets = new HashSet<>();
            if (col.length > 2 && !col[2].trim().isEmpty()) {
                for (String nom : col[2].split(";")) {
                    objets.add(new Objet(nom.trim()));
                }
            }

            pages.add(new Page(id, new Enigme(texte, temps), objets));
            id++;
        }

        reader.close();
        return pages;
    }
}