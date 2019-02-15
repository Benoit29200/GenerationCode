package modele.metamodeleJava.accesseur.setter;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.accesseur.Setter;

public class SetterCollection extends Setter {

    private String soustype;

    public SetterCollection(String nom, String type, String soustype) {
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
        generateur.generateCodeSetterCollection(this);
    }

    @Override
    public String toString() {
        return "SetterCollection{" +
                "soustype='" + soustype + '\'' +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
