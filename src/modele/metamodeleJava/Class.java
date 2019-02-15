package modele.metamodeleJava;

import java.util.ArrayList;

public class Class {

    private String nom;
    private String subtypeof;
    private ArrayList<Import> imports;
    private ArrayList<Constructor> constructors;
    private ArrayList<Accesseur> accesseurs;
    private ArrayList<Association> associations;

    public Class(String nom, String subtypeof) {
        this.nom = nom;
        this.subtypeof = subtypeof;
        this.imports = new ArrayList<>();
        this.constructors = new ArrayList<>();
        this.accesseurs = new ArrayList<>();
        this.associations = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSubtypeof() {
        return subtypeof;
    }

    public void setSubtypeof(String subtypeof) {
        this.subtypeof = subtypeof;
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

    public ArrayList<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(ArrayList<Association> attributs) {
        this.associations = attributs;
    }

    public ArrayList<Import> getImports() {
        return imports;
    }

    public void setImports(ArrayList<Import> imports) {
        this.imports = imports;
    }

    @Override
    public String toString() {
        return "Class{" +
                "nom='" + nom + '\'' +
                ", subtypeof='" + subtypeof + '\'' +
                ", imports=" + imports +
                ", constructors=" + constructors +
                ", accesseurs=" + accesseurs +
                ", associations=" + associations +
                '}';
    }
}
