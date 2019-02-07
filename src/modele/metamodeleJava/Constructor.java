package modele.metamodeleJava;

import generateur.java.GenerateurCodeJava;

public abstract class Constructor {

    protected String nom;

    public abstract String getNom();
    public abstract  void setNom(String nom);
    public abstract void generateCodeConstructor(GenerateurCodeJava generateur, StringBuffer code);
}
