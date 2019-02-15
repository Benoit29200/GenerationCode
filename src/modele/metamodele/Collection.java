package modele.metamodele;

import generateur.java.GenerateurMetaJava;

public class Collection extends AssoMultiple {

    private String soustype;

    public Collection(String nom, String type, String soustype, Entite parent, int cardMin, int cardMax) {
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
    public void visitForDeclaration(GenerateurMetaJava generateur) {
        generateur.generateDeclarationCollection(this);
    }

    @Override
    public void visitForGetterSetter(GenerateurMetaJava generateur) {
        generateur.generateGetterSetterCollection(this);
    }

    @Override
    public void visitForParamConstructor(GenerateurMetaJava generateur) {
        generateur.generateParamCollectionForConstructor(this);
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
