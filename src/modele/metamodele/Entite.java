package modele.metamodele;


import generateur.java.GenerateurMetaJava;

import java.util.ArrayList;

public class Entite {

    private String nom;
    private ArrayList<Association> associations;
    private String subtypeof;
    private Modele parent;

    public Entite(String nom, ArrayList<Association> associations, String subtypeof, Modele parent) {
        this.nom = nom;
        this.associations = associations;
        this.subtypeof = subtypeof;
        this.parent=parent;
    }

    public Entite(String nom, String subtypeof, Modele parent) {
        this.nom = nom;
        this.subtypeof = subtypeof;
        this.associations = new ArrayList<>();
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

    public ArrayList<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(ArrayList<Association> assoSimples) {
        this.associations = assoSimples;
    }

    public void addAssociation(Association ass){
        this.associations.add(ass);
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
                ", associations=" + associations +
                ", subtypeof='" + subtypeof + '\'' +
                ", parent=" + parent +
                '}';
    }

    public void visit(GenerateurMetaJava g){
        g.generateEntity(this);
    }
}
