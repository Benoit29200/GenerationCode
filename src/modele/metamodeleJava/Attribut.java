package modele.metamodeleJava;

public class Attribut {

    private String nom;
    private String type;
    private String value;

    public Attribut(String nom, String type, String value) {
        this.nom = nom;
        this.type = type;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AssoSimple{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
