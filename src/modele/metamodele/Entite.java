package modele.metamodele;


import generateur.java.GenerateurMetaJava;

import java.util.ArrayList;

public class Entite {

    private String nom;
    private ArrayList<Attribut> attributs;
    private String subtypeof;

    public Entite(String nom, ArrayList<Attribut> attributs, String subtypeof) {
        this.nom = nom;
        this.attributs = attributs;
        this.subtypeof = subtypeof;
    }

    public Entite(String nom, String subtypeof) {
        this.nom = nom;
        this.subtypeof = subtypeof;
        this.attributs = new ArrayList<>();
    }

    public Entite() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Attribut> getAttributs() {
        return attributs;
    }

    public void setAttributs(ArrayList<Attribut> attributs) {
        this.attributs = attributs;
    }

    public void addAttribut(Attribut att){
        this.attributs.add(att);
    }

    public String getSubtypeof() {
        return subtypeof;
    }

    public void setSubtypeof(String subtypeof) {
        this.subtypeof = subtypeof;
    }

    @Override
    public String toString() {
        return "Entite{" +
                "nom='" + nom + '\'' +
                ", attributs=" + attributs.toString() +
                ", subtypeof='" + subtypeof + '\'' +
                '}';
    }

    public void visit(GenerateurMetaJava g){
        g.generateEntity(this);
    }
}
