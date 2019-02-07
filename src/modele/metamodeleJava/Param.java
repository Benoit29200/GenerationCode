package modele.metamodeleJava;

public class Param {

    private String nom;
    private String type;

    public Param(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public Param() {
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
        return "Param{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
