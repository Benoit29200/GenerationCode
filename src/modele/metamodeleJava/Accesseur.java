package modele.metamodeleJava;

import generateur.java.GenerateurCodeJava;

public abstract class Accesseur {

    protected String nom;
    protected String type;

    public abstract String getNom();
    public abstract String getType();

    public abstract void setNom(String nom);
    public abstract void setType(String type);
    public abstract void generateAccesseur(GenerateurCodeJava generateur, StringBuffer code);
}
