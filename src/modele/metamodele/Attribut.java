package modele.metamodele;


import generateur.java.GenerateurMetaJava;

public class Attribut {

    private String nom;
    private String type;
    private String value;
    private Entite parent;

    public Attribut(String nom, String type, String value, Entite parent) {
        this.nom = nom;
        this.type = type;
        this.value = value;
        this.parent = parent;
    }

    public Attribut() {
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Entite getParent() {
        return parent;
    }

    public void setParent(Entite parent) {
        this.parent = parent;
    }

    public void visitForDeclaration(GenerateurMetaJava g){
        g.generateDeclarationAttribut(this);
    }

    public void visitForGetterSetter(GenerateurMetaJava g){
        g.generateGetterSetterAttribut(this);
    }

    public void visitForParamConstructor(GenerateurMetaJava g){
        g.generateParamForConstructor(this);
    }
}
