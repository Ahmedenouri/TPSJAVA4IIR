package ma.emsi.gestion;

public record ResultatEtudiant(int id, String nom, double note, boolean admis) {
    public ResultatEtudiant {
        if (note < 0.0 || note > 20.0) {
            throw new IllegalArgumentException("La note doit etre comprise entre 0 et 20.");
        }
    }
}
