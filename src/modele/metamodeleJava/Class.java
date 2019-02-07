package modele.metamodeleJava;

import java.util.ArrayList;

public class Class {

    private String nom;
    private ArrayList<Constructor> constructors;
    private ArrayList<Accesseur> accesseurs;
    private ArrayList<Attribut> attributs;

    public Class(String nom) {
        this.nom = nom;
        this.constructors = new ArrayList<>();
        this.accesseurs = new ArrayList<>();
        this.attributs = new ArrayList<>();
    }

    public Class() {
        this.constructors = new ArrayList<>();
        this.accesseurs = new ArrayList<>();
        this.attributs = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Constructor> getConstructors() {
        return constructors;
    }

    public void setConstructors(ArrayList<Constructor> constructors) {
        this.constructors = constructors;
    }

    public ArrayList<Accesseur> getAccesseurs() {
        return accesseurs;
    }

    public void setAccesseurs(ArrayList<Accesseur> accesseurs) {
        this.accesseurs = accesseurs;
    }

    public ArrayList<Attribut> getAttributs() {
        return attributs;
    }

    public void setAttributs(ArrayList<Attribut> attributs) {
        this.attributs = attributs;
    }

    @Override
    public String toString() {
        return "Class{" +
                "nom='" + nom + '\'' +
                ", constructors=" + constructors +
                ", accesseurs=" + accesseurs +
                ", attributs=" + attributs +
                '}';
    }
}
