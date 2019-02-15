package modele.metamodeleJava.param;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.Param;

public class ParamArray extends Param {

    public ParamArray(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    @Override
    public void generateCodeEnteteConstructorParam(GenerateurCodeJava generateur) {
        generateur.generateCodeEnteteConstructorParamArray(this);
    }

    @Override
    public String toString() {
        return "ParamArray{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
