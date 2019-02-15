package modele.metamodeleJava.accesseur.setter;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.accesseur.Setter;

public class SetterAttribut extends Setter {

    public SetterAttribut(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    @Override
    public void visitForGenerateAccesseur(GenerateurCodeJava generateur) {
        generateur.generateCodeSetterAttribut(this);
    }

    @Override
    public String toString() {
        return "SetterAttribut{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
