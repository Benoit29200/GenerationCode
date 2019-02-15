package modele.metamodeleJava;


import generateur.java.GenerateurCodeJava;

public abstract class Accesseur {

    protected String nom;
    protected String type;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract void visitForGenerateAccesseur(GenerateurCodeJava generateur);

}
