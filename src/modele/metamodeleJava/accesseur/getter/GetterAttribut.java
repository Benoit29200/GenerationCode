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
    public void generateAccesseur(GenerateurCodeJava generateur, StringBuffer code) {
        // TODO
        // generateur.generateCodeGetter(this,code);
    }

    @Override
    public String toString() {
        return "GetterAttribut{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
