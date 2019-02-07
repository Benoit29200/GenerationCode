package modele.metamodeleJava;

import generateur.GenerateurCodeJava;

import java.util.ArrayList;

public class ConstructorParams extends Constructor {

    ArrayList<Param> params;

    public ConstructorParams() {
    }

    public ConstructorParams(String nom) {
        this.nom = nom;
        this.params = new ArrayList<>();
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Param> getParams() {
        return params;
    }

    public void setParams(ArrayList<Param> params) {
        this.params = params;
    }

    @Override
    public void generateCodeConstructor(GenerateurCodeJava generateur, StringBuffer code) {
        generateur.generateCodeConstructorParams(this,code);
    }

    @Override
    public String toString() {
        return "ConstructorParams{" +
                "params=" + params +
                ", nom='" + nom + '\'' +
                '}';
    }
}
