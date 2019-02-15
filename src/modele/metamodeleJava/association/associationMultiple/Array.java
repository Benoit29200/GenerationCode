package modele.metamodeleJava.association.associationMultiple;

import generateur.java.GenerateurCodeJava;
import modele.metamodeleJava.Class;
import modele.metamodeleJava.association.AssoMultiple;

public class Array extends AssoMultiple {

    public Array(String nom, String type, Class parent, int cardMin, int cardMax) {
        this.nom = nom;
        this.type = type;
        this.parent = parent;
        this.cardMin = cardMin;
        this.cardMax = cardMax;
    }

    @Override
    public void visitForGenerateCodeAssociation(GenerateurCodeJava generateur) {
        generateur.generateCodeArray(this);
    }

    @Override
    public String toString() {
        return "Array{" +
                "cardMin=" + cardMin +
                ", cardMax=" + cardMax +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", parent=" + parent +
                '}';
    }
}
