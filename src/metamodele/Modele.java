package metamodele;

import generateur.Generateur;

import java.util.ArrayList;

public class Modele {

    String nom;
    ArrayList<Entite> entites;


    public Modele(String nom) {
        this.nom = nom;
        entites = new ArrayList<>();
    }

    public Modele(){}

    public String getNom() {
        return nom;
    }

    public ArrayList<Entite> getEntites() {
        return entites;
    }

}
