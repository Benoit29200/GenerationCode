package modele.metamodeleJava.param;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.Param;

public class ParamAttribut extends Param {

    public ParamAttribut(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    @Override
    public void generateCodeEnteteConstructorParam(GenerateurCodeJava generateur) {
        generateur.generateCodeEnteteConstructorParamAttribut(this);
    }

    @Override
    public String toString() {
        return "ParamAttribut{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
