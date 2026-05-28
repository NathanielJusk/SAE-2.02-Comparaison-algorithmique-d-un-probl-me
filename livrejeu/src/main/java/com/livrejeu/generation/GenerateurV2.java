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
 return new LivreJeu();
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
 int id = Integer.parseInt(scanner.nextLine().trim());

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
 int idPageDepart = Integer.parseInt(scanner.nextLine().trim());
 for (Page p : pagesChoisies) {
 if (p.getId() == idPageDepart) {
 livre.setPageDepart(p);
 }
 }

 System.out.println("ID de la page de SORTIE :");
 int idPageSortie = Integer.parseInt(scanner.nextLine().trim());
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

 System.out.println("Entrez les objets requis (nom exact, 'fin' pour terminer) :");
 Set<Objet> objetsRequis = new HashSet<>();
 while (true) {
 System.out.print(" Objet : ");
 String nom = scanner.nextLine().trim();
 if (nom.equals("fin"))
 break;
 objetsRequis.add(new Objet(nom));
 }
 livre.setObjetsRequis(objetsRequis);

 System.out.println("Livre cree avec " + livre.getNbPages() + " pages.");
 return livre;
 }
}