package modele.metamodeleJava;

public class Import {

    private String nom;

    public Import(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Import{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
