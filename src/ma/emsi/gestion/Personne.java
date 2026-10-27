package ma.emsi.gestion;

public abstract class Personne {
    private String nom;

    public Personne(String nom){
        this.nom = nom;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public abstract String role();
}
