package com.livrejeu;

import com.livrejeu.Enigme;
import com.livrejeu.Objet;
import com.livrejeu.Page;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

public class LecteurHistoire {

    public static List<Page> charger(String cheminFichier) throws IOException {
        List<Page> pages = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));

        reader.readLine();

        String ligne;
        int id = 1;

        while ((ligne = reader.readLine()) != null) {

            if (ligne.trim().isEmpty()) {
                continue;
            }

            String[] col = ligne.split(",");

            String texte = col[0].trim();
            int temps = Integer.parseInt(col[1].trim());

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
     public static void sauvegarder(LivreJeu livre, String fichier) throws IOException {

     
        DOTExporter<Page, DefaultWeightedEdge> exporter = new DOTExporter<>();

        exporter.setVertexAttributeProvider((p) ->
            Map.of("label", new DefaultAttribute<>(
                "P" + p.getId() + " - " + p.getEnigme().getTexte(),
                AttributeType.STRING))
        );

        exporter.setEdgeAttributeProvider((e) ->
            Map.of("label", new DefaultAttribute<>(
                String.valueOf((int) livre.getGraphe().getEdgeWeight(e)),
                AttributeType.STRING))
        );

        exporter.exportGraph(livre.getGraphe(), new FileWriter(fichier));

        System.out.println("Livre sauvegarde : " + fichier);
        System.out.println("Pour generer le PDF :");
        System.out.println("  dot -Tpdf " + fichier + " -o livre.pdf");
    }

}
