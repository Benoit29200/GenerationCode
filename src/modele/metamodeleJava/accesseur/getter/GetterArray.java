package modele.metamodeleJava.accesseur.getter;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.accesseur.Getter;

public class GetterArray extends Getter {

    public GetterArray(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }


    @Override
    public String toString() {
        return "GetterArray{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public void visitForGenerateAccesseur(GenerateurCodeJava generateur) {
        generateur.generateCodeGetterArray(this);
    }
}
