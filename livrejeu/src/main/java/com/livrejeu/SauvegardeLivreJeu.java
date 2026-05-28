package com.livrejeu;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SauvegardeLivreJeu {

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