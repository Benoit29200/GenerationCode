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


    public void toJava(){
        for(Entite entite:entites){
            entite.generateCodeEntiteToJava();
        }
    }

    public void acceptGenerateur(Generateur generateur){
        generateur.generateModeleToJava(this);
    }
}
