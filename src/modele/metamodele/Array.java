package modele.metamodele;

import generateur.java.GenerateurMetaJava;

public class Array extends AssoMultiple {

    public Array(String nom, String type, Entite parent, int cardMin, int cardMax) {
        this.nom = nom;
        this.type = type;
        this.parent = parent;
        this.cardMin = cardMin;
        this.cardMax = cardMax;
    }

    @Override
    public void visitForDeclaration(GenerateurMetaJava generateur) {
        generateur.generateDeclarationArray(this);
    }

    @Override
    public void visitForGetterSetter(GenerateurMetaJava generateur) {
        generateur.generateGetterSetterArray(this);
    }

    @Override
    public void visitForParamConstructor(GenerateurMetaJava generateur) {
        generateur.generateParamArrayForConstructor(this);
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
