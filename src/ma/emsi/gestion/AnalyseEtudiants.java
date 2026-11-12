package ma.emsi.gestion;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyseEtudiants {

    // ÉTAPE 1: De la boucle impérative au stream
    public static void afficherAdmis(List<Etudiant> etudiants, RegleAdmission regle) {
        etudiants.stream()
                .filter(regle::valider)
                .forEach(e -> System.out.println(e.getNom() + ": " + e.getNote()));
    }

    // ÉTAPE 2: Filtrer et transformer les données (Records & Noms)
    public static List<ResultatEtudiant> obtenirResultats(List<Etudiant> etudiants, RegleAdmission regle) {
        return etudiants.stream()
                .filter(regle::valider)
                .map(e -> new ResultatEtudiant(e.getId(), e.getNom(), e.getNote(), e.estAdmis()))
                .toList();
    }

    public static List<String> obtenirNomsAdmis(List<Etudiant> etudiants, RegleAdmission regle) {
        return etudiants.stream()
                .filter(regle::valider)
                .map(Etudiant::getNom)
                .toList();
    }

    // ÉTAPE 3: Trier, classer et limiter (Top 3 decroissant)
    public static void afficherTop3(List<Etudiant> etudiants) {
        etudiants.stream()
                .sorted(Comparator.comparingDouble(Etudiant::getNote).reversed())
                .limit(3)
                .forEach(e -> System.out.println(e.getNom() + ": " + e.getNote()));
    }

    // ÉTAPE 4: Recherches et statistiques
    public static void afficherStatistiques(List<Etudiant> etudiants, RegleAdmission regle) {
        long countAdmis = etudiants.stream().filter(regle::valider).count();

        boolean noteSup16 = etudiants.stream().anyMatch(e -> e.getNote() >= 16);
        boolean notesValides = etudiants.stream().allMatch(e -> e.getNote() >= 0 && e.getNote() <= 20);

        Etudiant meilleur = etudiants.stream()
                .max(Comparator.comparingDouble(Etudiant::getNote))
                .orElse(null);

        double moyenne = etudiants.stream()
                .mapToDouble(Etudiant::getNote)
                .average()
                .orElse(0.0);

        System.out.println("Admis: " + countAdmis);
        System.out.println("Note >= 16: " + noteSup16);
        System.out.println("Notes valides: " + notesValides);
        System.out.println("Meilleur: " + (meilleur != null ? meilleur.getNom() + " (" + meilleur.getNote() + ")" : "Aucun"));
        System.out.printf("Moyenne: %.2f\n", moyenne);
    }

    // ÉTAPE 5: Regrouper et produire un bilan
    public static void bilanParGroupes(List<Etudiant> etudiants, RegleAdmission regle) {
        // Partitioning By Admis / Ajourné
        Map<Boolean, List<Etudiant>> partition = etudiants.stream()
                .collect(Collectors.partitioningBy(regle::valider));

        List<String> nomsAdmis = partition.get(true).stream().map(Etudiant::getNom).toList();
        List<String> nomsAjournes = partition.get(false).stream().map(Etudiant::getNom).toList();

        System.out.println("Admis: " + nomsAdmis);
        System.out.println("Ajournés: " + nomsAjournes);

        // Grouping By Niveaux: Très bien (>=16), Admis (>=10 et <16), Ajourné (<10)
        Map<String, List<Etudiant>> groupesNiveaux = etudiants.stream()
                .collect(Collectors.groupingBy(e -> {
                    if (e.getNote() >= 16) return "Très bien";
                    else if (e.getNote() >= 10) return "Admis";
                    else return "Ajourné";
                }));

        System.out.println("Très bien: " + groupesNiveaux.getOrDefault("Très bien", List.of()).stream().map(Etudiant::getNom).toList());
    }
}