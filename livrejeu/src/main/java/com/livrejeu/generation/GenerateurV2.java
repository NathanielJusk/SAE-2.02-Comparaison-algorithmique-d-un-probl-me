package com.livrejeu.generation;

import com.livrejeu.LivreJeu;
import com.livrejeu.Objet;
import com.livrejeu.Page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GenerateurV2 implements IGenerateur {

    @Override
    public LivreJeu generer(int nbPages, int idDepart, int idSortie,
            Map<Integer, Set<Objet>> objets) {
        LivreJeu livre = new LivreJeu();
        List<Page> pagesChoisies = new ArrayList<>();
        
        for (int i = 1; i <= nbPages; i++) {
            Set<Objet> pageObjets = objets != null && objets.containsKey(i) ? objets.get(i) : new HashSet<>();
            Page p = new Page(i, new com.livrejeu.Enigme("Enigme " + i, 1), pageObjets);
            livre.ajouterPage(p);
            pagesChoisies.add(p);
            
            if (i == idDepart) {
                livre.setPageDepart(p);
            }
            if (i == idSortie) {
                livre.setPageSortie(p);
            }
        }
        
        for (int i = 0; i < pagesChoisies.size() - 1; i++) {
            livre.ajouterLien(pagesChoisies.get(i), pagesChoisies.get(i + 1));
        }
        
        return livre;
    }

    public LivreJeu genererDepuisPages(List<Page> pagesDisponibles, int nbPages, Scanner scanner) {

        LivreJeu livre = new LivreJeu();
        List<Page> pagesChoisies = new ArrayList<>();

        System.out.println("Pages disponibles :");
        for (Page p : pagesDisponibles) {
            System.out.println(" [" + p.getId() + "] " + p.getEnigme().getTexte());
        }

        System.out.println("Choisissez " + nbPages + " pages (entrez leur ID) :");
        for (int i = 0; i < nbPages; i++) {
            System.out.print(" Page " + (i + 1) + " : ");
            int id = scanner.nextInt();
            scanner.nextLine(); // vider le buffer

            Page pageTrouvee = null;
            for (Page p : pagesDisponibles) {
                if (p.getId() == id) {
                    pageTrouvee = p;
                }
            }

            if (pageTrouvee == null) {
                System.out.println(" ID introuvable, on recommence.");
                i--;
            } else {
                pagesChoisies.add(pageTrouvee);
                livre.ajouterPage(pageTrouvee);
            }
        }

        System.out.println("ID de la page de DEPART :");
        int idPageDepart = scanner.nextInt();
        scanner.nextLine();
        for (Page p : pagesChoisies) {
            if (p.getId() == idPageDepart) {
                livre.setPageDepart(p);
            }
        }

        System.out.println("ID de la page de SORTIE :");
        int idPageSortie = scanner.nextInt();
        scanner.nextLine();
        for (Page p : pagesChoisies) {
            if (p.getId() == idPageSortie) {
                livre.setPageSortie(p);
            }
        }

        System.out.println("Ajout des liens. Tapez 'fin' pour terminer.");
        while (true) {
            System.out.print(" ID source (ou 'fin') : ");
            String inputSrc = scanner.nextLine().trim();
            if (inputSrc.equals("fin"))
                break;

            System.out.print(" ID destination : ");
            int idDst = Integer.parseInt(scanner.nextLine().trim());
            int idSrc = Integer.parseInt(inputSrc);

            Page src = null;
            Page dst = null;
            for (Page p : pagesChoisies) {
                if (p.getId() == idSrc)
                    src = p;
                if (p.getId() == idDst)
                    dst = p;
            }

            if (src != null && dst != null) {
                livre.ajouterLien(src, dst);
                System.out.println(" Lien ajoute : P" + idSrc + " -> P" + idDst);
            } else {
                System.out.println(" ID invalide.");
            }
        }

    Set<Objet> objetsDisponibles = new HashSet<>();
    for (Page p : pagesChoisies) {
        objetsDisponibles.addAll(p.getObjets());
    }

    Set<Objet> objetsRequis = new HashSet<>();

    if (objetsDisponibles.isEmpty()) {
        System.out.println("Aucun objet disponible dans les pages choisies.");
    } else {
        // Afficher uniquement les objets qui existent
        System.out.println("Objets disponibles :");
        for (Objet o : objetsDisponibles) {
            System.out.println("  - " + o.getNom());
        }

        System.out.println("Objets requis ('fin' pour terminer) :");
        while (true) {
            System.out.print("  Objet : ");
            String nom = scanner.nextLine().trim();
            if (nom.equals("fin")) break;

            Objet saisi = new Objet(nom);
            // Vérifier que l'objet existe dans les pages choisies
            if (objetsDisponibles.contains(saisi)) {
                objetsRequis.add(saisi);
                System.out.println("  OK : " + nom + " ajoute.");
            } else {
                System.out.println("  Objet introuvable dans les pages choisies, reessayez.");
            }
        }
}

        livre.setObjetsRequis(objetsRequis);
        return livre;
    }
}