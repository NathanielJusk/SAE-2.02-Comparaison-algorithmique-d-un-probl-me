package com.livrejeu;

import com.livrejeu.algorithme.AlgorithmeBFS;
import com.livrejeu.algorithme.AlgorithmeDijkstra;
import com.livrejeu.generation.GenerateurV1;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AppLivreJeu {

    private LivreJeu livre;

    // ── saisir un entier ──
    private static int saisirNbPages(Scanner scanner) {
        System.out.print("Nombre de pages : ");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    // ── main ──
    public static void main(String[] args) {
        AppLivreJeu app = new AppLivreJeu();
        Scanner scanner = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("    LIVRE-JEU ALGORITHMIQUE    ");
        System.out.println("================================");

        // Etape 1 — Charger le CSV
        System.out.print("Chemin du fichier histoire.csv : ");
        String cheminCsv = "livrejeu/src/main/java/com/livrejeu/histoireCsv/histoire.csv";

        List<Page> pagesDisponibles;
        try {
            pagesDisponibles = LecteurHistoire.charger(cheminCsv);
            System.out.println(pagesDisponibles.size() + " pages chargees.");
        } catch (IOException e) {
            System.out.println("Erreur : impossible de charger le fichier.");
            System.out.println(e.getMessage());
            return;
        }

        // Etape 2 — Menu
        boolean continuer = true;
        while (continuer) {
            System.out.println();
            System.out.println("--------------------------------");
            System.out.println("           MENU                ");
            System.out.println("--------------------------------");
            System.out.println("1. Generer un livre (aleatoire  - V1)");
            System.out.println("2. Generer un livre (interactif - V2)");
            System.out.println("3. Afficher le livre");
            System.out.println("4. Resoudre");
            System.out.println("5. Sauvegarder (CSV)");
            System.out.println("6. Exporter (.dot)");
            System.out.println("0. Quitter");
            System.out.println("--------------------------------");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine().trim();
            switch (choix) {

                case "1" -> {
                    int n = saisirNbPages(scanner);
                    app.livre = new GenerateurV1()
                            .genererDepuisPages(pagesDisponibles, n);
                    System.out.println("Livre genere avec " + n + " pages.");
                }

                case "2" -> {
                    int n = saisirNbPages(scanner);
                    app.livre = new GenerateurV2()
                            .genererDepuisPages(pagesDisponibles, n, scanner);
                }

                case "3" -> {
                    if (app.livre == null) {
                        System.out.println("Aucun livre en memoire.");
                        break;
                    }
                    System.out.println(app.livre);
                }

                case "4" -> {
                    if (app.livre == null) {
                        System.out.println("Aucun livre en memoire.");
                        break;
                    }

                    System.out.println("Choisissez l'algorithme :");
                    System.out.println("  1. Dijkstra");
                    System.out.println("  2. BFS");
                    System.out.print("Votre choix : ");
                    String choixAlgo = scanner.nextLine().trim();

                    Solution sol = null;

                    if (choixAlgo.equals("1")) {
                        System.out.println("Resolution en cours avec Dijkstra...");
                        sol = new AlgorithmeDijkstra().resoudre(app.livre);
                    } else if (choixAlgo.equals("2")) {
                        System.out.println("Resolution en cours avec BFS...");
                        sol = new AlgorithmeBFS().resoudre(app.livre);
                    } else {
                        System.out.println("Choix invalide.");
                        break;
                    }

                    if (sol != null) {
                        System.out.println(sol);
                    } else {
                        System.out.println("Aucune solution trouvee.");
                    }
                }

                case "5" -> {
                    if (app.livre == null) {
                        System.out.println("Aucun livre en memoire.");
                        break;
                    }
                    System.out.print("Nom du fichier CSV : ");
                    try {
                        SauvegardeLivreJeu.sauvegarder(app.livre, scanner.nextLine().trim());
                    } catch (IOException e) {
                        System.out.println("Erreur : impossible de sauvegarder le fichier.");
                    }
                }

                case "0" -> continuer = false;

                default -> System.out.println("Choix invalide.");
            }
        }

        System.out.println("Au revoir.");
        scanner.close();
    }
}