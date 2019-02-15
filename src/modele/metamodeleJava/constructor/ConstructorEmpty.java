package modele.metamodeleJava.constructor;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.Constructor;

public class ConstructorEmpty extends Constructor {


    public ConstructorEmpty(String nom){
        this.nom = nom;
    }

    @Override
    public void generateCodeConstructor(GenerateurCodeJava generateur, StringBuffer code) {
        generateur.generateCodeConstructorEmpty(this,code);
    }

    @Override
    public String toString() {
        return "ConstructorEmpty{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
