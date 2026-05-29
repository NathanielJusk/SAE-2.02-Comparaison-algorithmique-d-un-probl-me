package com.livrejeu;

import com.livrejeu.algorithme.AlgorithmeBFS;
import com.livrejeu.algorithme.AlgorithmeDijkstra;
import com.livrejeu.generation.GenerateurV1;
import com.livrejeu.generation.GenerateurV2;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AppLivreJeu {

    private LivreJeu livre;

    private static int saisirNbPages(Scanner scanner) {
        System.out.print("Nombre de pages : ");
        int nb = scanner.nextInt();
        scanner.nextLine(); // vider le buffer
        return nb;
    }

    public static void main(String[] args) {
        AppLivreJeu app = new AppLivreJeu();
        Scanner scanner = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("    LIVRE-JEU ALGORITHMIQUE    ");
        System.out.println("================================");
        System.out.println("Bienvenue ! Suivez les etapes pour creer et resoudre votre livre-jeu.");
        System.out.println();
        System.out.println(">> ETAPE 1 : Chargement de l'histoire...");

        String cheminCsv = "livrejeu/src/main/java/com/livrejeu/histoireCsv/histoire.csv";
        List<Page> pagesDisponibles;
        try {
            pagesDisponibles = LecteurHistoire.charger(cheminCsv);
            System.out.println("   OK : " + pagesDisponibles.size() + " pages chargees depuis le CSV.");
        } catch (IOException e) {
            System.out.println("   ERREUR : impossible de charger le fichier.");
            System.out.println(e.getMessage());
            return;
        }

        System.out.println();
        System.out.println(">> ETAPE 2 : Generez votre livre (choix 1 ou 2).");
        System.out.println(">> ETAPE 3 : Affichez le livre (choix 3) pour verifier.");
        System.out.println(">> ETAPE 4 : Resolvez avec Dijkstra ou BFS (choix 4).");
        System.out.println(">> ETAPE 5 : Sauvegardez en CSV (choix 5).");
        System.out.println();

        boolean continuer = true;
        while (continuer) {
            System.out.println("--------------------------------");
            System.out.println("           MENU                ");
            System.out.println("--------------------------------");
            System.out.println("1. Generer un livre (aleatoire  - V1)");
            System.out.println("2. Generer un livre (interactif - V2)");
            System.out.println("3. Afficher le livre");
            System.out.println("4. Resoudre");
            System.out.println("5. Sauvegarder (CSV)");

            System.out.println("0. Quitter");
            System.out.println("--------------------------------");

            if (app.livre == null) {
                System.out.println("[!] Aucun livre en memoire — commencez par le choix 1 ou 2");
            } else {
                System.out.println("[OK] Livre en memoire : " + app.livre.getNbPages() + " pages");
            }
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // vider le buffer

            switch (choix) {

                case 1: 
                    System.out.println(">> Generation aleatoire (V1)");
                    System.out.println("   Le livre sera genere automatiquement.");
                    int n = saisirNbPages(scanner);
                    app.livre = new GenerateurV1().genererDepuisPages(pagesDisponibles, n);
                    System.out.println("   OK : Livre genere avec " + n + " pages.");
                    System.out.println("   --> Passez au choix 3 pour afficher votre livre.");
                    break;

                case 2:
                    System.out.println(">> Generation interactive (V2)");
                    System.out.println("   Vous allez choisir vos pages, les liens et les objets requis.");
                    int n2 = saisirNbPages(scanner);
                    app.livre = new GenerateurV2().genererDepuisPages(pagesDisponibles, n2, scanner);
                    System.out.println("   --> Passez au choix 3 pour afficher votre livre.");
                    break;

                case 3:
                    System.out.println(">> Affichage du livre");
                    if (app.livre == null) {
                        System.out.println("   [!] Aucun livre en memoire. Faites d'abord le choix 1 ou 2.");
                        break;
                    }
                    System.out.println(app.livre);
                    System.out.println("   --> Passez au choix 4 pour resoudre.");
                    break;

                case 4:
                    System.out.println(">> Resolution");
                    if (app.livre == null) {
                        System.out.println("   [!] Aucun livre en memoire. Faites d'abord le choix 1 ou 2.");
                        break;
                    }
                    System.out.println("   Choisissez l'algorithme :");
                    System.out.println("     1. Dijkstra — chemin le plus RAPIDE (moins de temps total)");
                    System.out.println("     2. BFS      — chemin le plus COURT  (moins d'etapes)");
                    System.out.print("   Votre choix : ");
                    int choixAlgo = scanner.nextInt();
                    scanner.nextLine();

                    Solution sol = null;
                    if (choixAlgo == 1) {
                        System.out.println("   Resolution avec Dijkstra...");
                        sol = new AlgorithmeDijkstra().resoudre(app.livre);
                    } else if (choixAlgo == 2) {
                        System.out.println("   Resolution avec BFS...");
                        sol = new AlgorithmeBFS().resoudre(app.livre);
                    } else {
                        System.out.println("   Choix invalide.");
                        break;
                    }

                    if (sol != null) {
                        System.out.println(sol);
                        System.out.println("   --> Passez au choix 5 pour sauvegarder.");
                    } else {
                        System.out.println("   Aucune solution trouvee.");
                        System.out.println("   [!] Verifiez que vos liens forment un chemin du depart a la sortie.");
                        System.out.println("   [!] Verifiez que les objets requis sont dans les pages du chemin.");
                    }
                    break;

                case 5:
                    System.out.println(">> Sauvegarde (CSV)");
                    if (app.livre == null) {
                        System.out.println("   [!] Aucun livre en memoire.");
                        break;
                    }
                    System.out.print("   Nom du fichier (ex: monlivre.csv) : ");
                    try {
                        String nom = scanner.nextLine();
                        SauvegardeLivreJeu.sauvegarder(app.livre, nom);
                    } catch (IOException e) {
                        System.out.println("   Erreur : " + e.getMessage());
                    }
                    break;

                case 0: 
                    continuer = false;
                    break;

                default : 
                    System.out.println("[!] Choix invalide. Entrez un chiffre entre 0 et 5.");
                    break;
            }
            System.out.println();
        }

        System.out.println("Au revoir !");
        scanner.close();
    }
}