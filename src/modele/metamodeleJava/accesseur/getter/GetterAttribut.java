package modele.metamodeleJava.accesseur.getter;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.accesseur.Getter;

public class GetterAttribut extends Getter {

    public GetterAttribut(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public GetterAttribut() {
    }

    @Override
    public void visitForGenerateAccesseur(GenerateurCodeJava generateur) {
        generateur.generateCodeGetterAttribut(this);
    }

    @Override
    public String toString() {
        return "GetterAttribut{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
