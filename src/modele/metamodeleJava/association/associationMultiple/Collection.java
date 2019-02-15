package modele.metamodeleJava.association.associationMultiple;


import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.Class;
import modele.metamodeleJava.association.AssoMultiple;

public class Collection extends AssoMultiple {

    private String soustype;

    public Collection(String nom, String type, String soustype, Class parent, int cardMin, int cardMax) {
        this.nom = nom;
        this.type = type;
        this.soustype = soustype;
        this.parent = parent;
        this.cardMin = cardMin;
        this.cardMax = cardMax;
    }

    public String getSoustype() {
        return soustype;
    }

    public void setSoustype(String soustype) {
        this.soustype = soustype;
    }

    @Override
    public void visitForGenerateCodeAssociation(GenerateurCodeJava generateur) {
        generateur.generateCodeCollection(this);
    }

    @Override
    public String toString() {
        return "Collection{" +
                "soustype='" + soustype + '\'' +
                ", cardMin=" + cardMin +
                ", cardMax=" + cardMax +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", parent=" + parent +
                '}';
    }
}
