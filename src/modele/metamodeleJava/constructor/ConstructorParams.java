package modele.metamodeleJava.constructor;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.Constructor;
import modele.metamodeleJava.Param;

import java.util.ArrayList;

public class ConstructorParams extends Constructor {

    private ArrayList<Param> params;

    public ConstructorParams(String nom) {
        this.nom = nom;
        this.params = new ArrayList<>();
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
