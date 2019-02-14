package modele.metamodele;


import generateur.java.GenerateurMetaJava;

import java.util.ArrayList;

public class Entite {

    private String nom;
    private ArrayList<Attribut> attributs;
    private String subtypeof;
    private Modele parent;

    public Entite(String nom, ArrayList<Attribut> attributs, String subtypeof, Modele parent) {
        this.nom = nom;
        this.attributs = attributs;
        this.subtypeof = subtypeof;
        this.parent=parent;
    }

    public Entite(String nom, String subtypeof, Modele parent) {
        this.nom = nom;
        this.subtypeof = subtypeof;
        this.attributs = new ArrayList<>();
        this.parent = parent;
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

    public Modele getParent() {
        return parent;
    }

    public void setParent(Modele parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Entite{" +
                "nom='" + nom + '\'' +
                ", attributs=" + attributs +
                ", subtypeof='" + subtypeof + '\'' +
                ", parent=" + parent +
                '}';
    }

    public void visit(GenerateurMetaJava g){
        g.generateEntity(this);
    }
}
