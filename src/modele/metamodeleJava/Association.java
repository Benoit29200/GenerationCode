package modele.metamodeleJava;

import generateur.java.GenerateurCodeJava;
import modele.metamodele.Entite;

public abstract class Association {

    protected String nom;
    protected String type;
    protected Class parent;

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

    public Class getParent() {
        return parent;
    }

    public void setParent(Class parent) {
        this.parent = parent;
    }

    public abstract void visitForGenerateCodeAssociation(GenerateurCodeJava generateur);

}
