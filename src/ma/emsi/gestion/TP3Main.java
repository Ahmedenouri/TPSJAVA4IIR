package ma.emsi.gestion;

import java.util.Arrays;
import java.util.List;

public class TP3Main {
    public static void main(String[] args) {
        // 1. Jeu d'essai (6 étudiants)
        List<Etudiant> etudiants = Arrays.asList(
                new Etudiant(1, "Amina", 15.0),
                new Etudiant(2, "Youssef", 9.0),
                new Etudiant(3, "Sara", 12.5),
                new Etudiant(4, "Omar", 14.0),
                new Etudiant(5, "Lina", 18.0),
                new Etudiant(6, "Karim", 7.5)
        );

        RegleAdmission regle = e -> e.getNote() >= 10.0;

        System.out.println("--- ÉTAPE 1: Affichage des Admis ---");
        AnalyseEtudiants.afficherAdmis(etudiants, regle);

        System.out.println("\n--- ÉTAPE 2: Transformation en Records ---");
        var resultats = AnalyseEtudiants.obtenirResultats(etudiants, regle);
        resultats.forEach(System.out::println);
        System.out.println("Noms admis: " + AnalyseEtudiants.obtenirNomsAdmis(etudiants, regle));

        System.out.println("\n--- ÉTAPE 3: Top 3 Étudiants ---");
        AnalyseEtudiants.afficherTop3(etudiants);

        System.out.println("\n--- ÉTAPE 4: Statistiques ---");
        AnalyseEtudiants.afficherStatistiques(etudiants, regle);

        System.out.println("\n--- ÉTAPE 5: Bilan et Groupes ---");
        AnalyseEtudiants.bilanParGroupes(etudiants, regle);
    }
}