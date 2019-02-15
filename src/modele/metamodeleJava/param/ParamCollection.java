package modele.metamodeleJava.param;

import modele.metamodeleJava.Param;

public class ParamCollection extends Param {

    private String soustype;

    public ParamCollection(String nom, String type, String soustype) {
        this.nom = nom;
        this.type = type;
        this.soustype = soustype;
    }

    public String getSoustype() {
        return soustype;
    }

    public void setSoustype(String soustype) {
        this.soustype = soustype;
    }

    @Override
    public String toString() {
        return "ParamCollection{" +
                "soustype='" + soustype + '\'' +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
