package modele.metamodeleJava;

public class Attribut {

    private String nom;
    private String type;

    public Attribut(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public Attribut() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Attribut{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
