package modele.metamodeleJava;

public class Getter extends Accesseur {

    public Getter(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public Getter() {
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
        return "Getter{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
