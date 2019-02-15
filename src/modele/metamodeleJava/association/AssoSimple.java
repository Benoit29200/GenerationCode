package modele.metamodeleJava.association;


import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.Association;
import modele.metamodeleJava.Class;

public class AssoSimple extends Association {

    private String value;

    public AssoSimple(String nom, String type, String value, Class parent) {
        this.nom = nom;
        this.type = type;
        this.value = value;
        this.parent = parent;
    }

    public AssoSimple() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void visitForGenerateCodeAssociation(GenerateurCodeJava generateur) {
        generateur.generateCodeAttribut(this);
    }

    @Override
    public String toString() {
        return "AssoSimple{" +
                "value='" + value + '\'' +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", parent=" + parent +
                '}';
    }
}
