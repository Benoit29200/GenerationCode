package modele.metamodeleJava.accesseur.setter;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.accesseur.Setter;

public class SetterArray extends Setter {

    public SetterArray(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    @Override
    public void visitForGenerateAccesseur(GenerateurCodeJava generateur) {
        generateur.generateCodeSetterArray(this);
    }

    @Override
    public String toString() {
        return "SetterArray{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
