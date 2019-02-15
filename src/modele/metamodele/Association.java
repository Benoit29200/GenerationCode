package modele.metamodele;

import generateur.java.GenerateurMetaJava;

public abstract class Association {

    protected String nom;
    protected String type;
    protected Entite parent;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Entite getParent() {
        return parent;
    }

    public void setParent(Entite parent) {
        this.parent = parent;
    }

    public abstract void visitForDeclaration(GenerateurMetaJava generateur);
    public abstract void visitForGetterSetter(GenerateurMetaJava generateur);
    public abstract void visitForParamConstructor(GenerateurMetaJava generateur);
}
