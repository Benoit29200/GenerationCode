package modele.metamodele;


import generateur.GenerateurMetaJava;

public class Attribut {

    private String nom;
    private String type;

    public Attribut(String nom, String type) {
        this.nom = nom;
        this.type = type;
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
