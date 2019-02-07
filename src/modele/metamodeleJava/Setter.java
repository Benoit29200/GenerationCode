package modele.metamodeleJava;

public class Setter extends Accesseur{


    public Setter(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public Setter() {
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
        return "Setter{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
