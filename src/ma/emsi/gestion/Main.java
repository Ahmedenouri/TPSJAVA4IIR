
package ma.emsi.gestion;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    // Question 6 : Recherche par nom
    public static Etudiant rechercherParNom(List<Etudiant> etudiants, String nom) {
        for (Etudiant e : etudiants) {
            if (e.getNom().trim()
                    .equalsIgnoreCase(nom.trim())) {
                return e;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        // Question 1 : Demarrage
        System.out.println("Gestion des étudiants — EMSI");

        // Question 2 et 5 : Creation de la liste
        List<Etudiant> etudiants = new ArrayList<>();

        etudiants.add(new Etudiant(1, "Amina", 15));
        etudiants.add(new Etudiant(2, "Youssef", 9));
        etudiants.add(new Etudiant(3, "Sara", 12.5));

        // Question 3 : Heritage et polymorphisme
        Personne p = etudiants.get(0);
        System.out.println("Role : " + p.role());

        // Question 4 : Interface
        Evaluatable ev = etudiants.get(0);
        System.out.println("Admis : " + ev.estAdmis());

        // Question 5 : Affichage et moyenne
        System.out.println("\nListe des étudiants :");

        double somme = 0;

        for (Etudiant e : etudiants) {
            System.out.println(e.getNom() + " : " + e.getNote());
            somme += e.getNote();
        }

        if (etudiants.isEmpty()) {
            System.out.println("La liste est vide.");
        } else {
            double moyenne = somme / etudiants.size();
            System.out.printf("Moyenne : %.2f%n", moyenne);
        }

        System.out.println("\nÉtudiants admis :");

        for (Etudiant e : etudiants) {
            if (e.estAdmis()) {
                System.out.println(e.getNom());
            }
        }

        // Question 6 : Recherche
        System.out.println("\nRecherche par nom :");

        Etudiant resultat = rechercherParNom(etudiants, "  amina");

        if (resultat != null) {
            System.out.println("Trouvé : " + resultat);
        } else {
            System.out.println("Étudiant introuvable.");
        }

        // Question 7 : Gestion des exceptions
        System.out.println("\nTest d'une note invalide :");

        try {
            etudiants.get(0).setNote(-2);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        // Bonus : Scanner et NumberFormatException
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Entrez un identifiant : ");
            String texte = sc.nextLine();

            int identifiant = Integer.parseInt(texte);
            System.out.println("Identifiant saisi : " + identifiant);

        } catch (NumberFormatException e) {
            System.out.println("Erreur : identifiant invalide.");
        }

        // Question 8 : HashSet
        System.out.println("\nTest du HashSet :");

        Set<Etudiant> ensemble = new HashSet<>();

        ensemble.add(new Etudiant(1, "Amina", 15));
        ensemble.add(new Etudiant(1, "Amina bis", 18));

        System.out.println("Taille du HashSet : " + ensemble.size());

        // Map : retrouver l'etudiant d'id 2
        Map<Integer, Etudiant> map = new HashMap<>();

        for (Etudiant e : etudiants) {
            map.put(e.getId(), e);
        }

        System.out.println("Etudiant id 2 : " + map.get(2));

        // Question 9 : Tri par identifiant
        List<Etudiant> triId = new ArrayList<>(etudiants);

        Collections.sort(triId);

        System.out.println("\nTri par id :");

        for (Etudiant e : triId) {
            System.out.println(e);
        }

        // Tri par note croissante avec classe anonyme
        List<Etudiant> triNote = new ArrayList<>(etudiants);

        Collections.sort(triNote, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                return Double.compare(
                        e1.getNote(), e2.getNote()
                );
            }
        });

        System.out.println("\nTri par note croissante :");

        for (Etudiant e : triNote) {
            System.out.println(e);
        }

        sc.close();
    }
}
