package modele.metamodeleJava.accesseur.getter;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.accesseur.Getter;

public class GetterCollection extends Getter {

    private String soustype;

    public GetterCollection(String nom, String type, String soustype) {
        this.nom = nom;
        this.type = type;
        this.soustype = soustype;
    }

    public String getSoustype() {
        return soustype;
    }

    public void setSoustype(String soustype) {
        this.soustype = soustype;
    }

    @Override
    public void visitForGenerateAccesseur(GenerateurCodeJava generateur) {
        generateur.generateCodeGetterCollection(this);
    }

    @Override
    public String toString() {
        return "GetterCollection{" +
                "soustype='" + soustype + '\'' +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
