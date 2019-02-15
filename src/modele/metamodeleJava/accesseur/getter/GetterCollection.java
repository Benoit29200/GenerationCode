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

    @Override
    public void generateAccesseur(GenerateurCodeJava generateur, StringBuffer code) {
        // TODO
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
