package ma.emsi.gestion;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TP2Main {

    public static boolean noteAdmise(double note) {
        return note >= 10.0;
    }

    public static void verifier(Etudiant e, RegleAdmission regle) {
        boolean res = regle.valider(e);
        System.out.println(e.getNom() + " (" + e.getNote() + "): " + res);
    }

    public static void main(String[] args) {

        System.out.println("=== ÉTAPE 1 ===");
        Etudiant youssef = new Etudiant(2, "Youssef", 9.0);
        System.out.println("Note initiale: " + youssef.getNote());
        youssef.setNote(11.0);
        System.out.println("Note modifiee: " + youssef.getNote());
        System.out.println("" + noteAdmise(9.0));

        System.out.println("\n=== ÉTAPE 2 ===");
        Etudiant amina = new Etudiant(1, "Amina", 15.0);
        Etudiant sara = new Etudiant(3, "Sara", 12.5);

        //Lambda
        RegleAdmission regleLambda = e -> e.getNote() >= 10.0;
        verifier(amina, regleLambda);
        verifier(youssef, regleLambda);

        System.out.println("\n=== ÉTAPE 3 ===");
        Predicate<Etudiant> estAdmis = e -> e.getNote() >= 10.0;
        Function<Etudiant, String> mention = e -> estAdmis.test(e) ? "Admis" : "Ajourne";
        Consumer<Etudiant> afficherDetails = e -> System.out.println(e.getNom() + ": " + e.getNote());
        Supplier<Etudiant> creerOmar = () -> new Etudiant(4, "Omar", 14.0);

        afficherDetails.accept(amina);
        System.out.println("Amina statut: " + mention.apply(amina));
        System.out.println("Youssef statut: " + mention.apply(youssef));
        Etudiant omar = creerOmar.get();
        System.out.println("Omar note (Supplier): " + omar.getNote());

        System.out.println("\n=== ÉTAPE 4 ===");
        Consumer<String> afficheur = System.out::println;
        afficheur.accept("Test System.out::println reussi.");

        Function<Etudiant, String> getNomFunc = Etudiant::getNom;
        System.out.println("Nom via Method Reference: " + getNomFunc.apply(amina));

        ResultatEtudiant resAmina = new ResultatEtudiant(amina.getId(), amina.getNom(), amina.getNote(), estAdmis.test(amina));
        System.out.println(resAmina);
        System.out.println("Acces au nom() dyal Record: " + resAmina.nom());

        System.out.println("\n=== ÉTAPE 5 ===");
        Etudiant[] etudiants = { amina, youssef, sara };

        for (Etudiant e : etudiants) {
            if (estAdmis.test(e)) {
                ResultatEtudiant resultat = new ResultatEtudiant(e.getId(), e.getNom(), e.getNote(), true);
                Consumer<ResultatEtudiant> afficherResultat = r -> System.out.println("Etudiant Admis -> Nom: " + r.nom() + ", Note: " + r.note());
                afficherResultat.accept(resultat);
            }
        }
    }
}