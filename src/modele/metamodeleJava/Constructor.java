package modele.metamodeleJava;

import generateur.java.GenerateurCodeJava;

public abstract class Constructor {

    protected String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public abstract void generateCodeConstructor(GenerateurCodeJava generateur, StringBuffer code);
}
