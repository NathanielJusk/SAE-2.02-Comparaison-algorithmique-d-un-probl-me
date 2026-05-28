package com.livrejeu.generation;

import com.livrejeu.LivreJeu;
import com.livrejeu.Objet;
import com.livrejeu.Page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenerateurV1 implements IGenerateur {

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

    public LivreJeu genererDepuisPages(List<Page> pagesDisponibles, int nbPages) {
        List<Page> copie = new ArrayList<>(pagesDisponibles);
        Collections.shuffle(copie);

        List<Page> pagesChoisies = new ArrayList<>();
        for (int i = 0; i < nbPages && i < copie.size(); i++) {
            pagesChoisies.add(copie.get(i));
        }

        if (pagesChoisies.size() < 2) {
            System.out.println("Pas assez de pages disponibles dans le fichier.");
            return new LivreJeu();
        }

        return construireLivre(pagesChoisies);
    }

    public static LivreJeu construireLivre(List<Page> pagesChoisies) {
        LivreJeu livre = new LivreJeu();

        for (int i = 0; i < pagesChoisies.size(); i++) {
            Page ancienne = pagesChoisies.get(i);
            Page nouvelle = new Page(i + 1, ancienne.getEnigme(), ancienne.getObjets());
            pagesChoisies.set(i, nouvelle);
            livre.ajouterPage(nouvelle);
        }

        Page depart = pagesChoisies.get(0);
        Page sortie = pagesChoisies.get(pagesChoisies.size() - 1);
        livre.setPageDepart(depart);
        livre.setPageSortie(sortie);

        for (int i = 0; i < pagesChoisies.size() - 1; i++) {
            livre.ajouterLien(pagesChoisies.get(i), pagesChoisies.get(i + 1));

            if (i % 3 == 0 && !pagesChoisies.get(i).equals(depart)) {
                livre.ajouterLien(pagesChoisies.get(i), depart);
            }
        }

        Set<Objet> objetsRequis = new HashSet<>();
        for (Page p : pagesChoisies) {
            objetsRequis.addAll(p.getObjets());
        }
        livre.setObjetsRequis(objetsRequis);

        return livre;
    }
}