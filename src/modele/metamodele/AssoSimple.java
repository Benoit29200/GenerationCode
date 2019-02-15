package modele.metamodele;


import generateur.java.GenerateurMetaJava;

public class AssoSimple extends Association {

    private String value;

    public AssoSimple(String nom, String type, String value, Entite parent) {
        this.nom = nom;
        this.type = type;
        this.value = value;
        this.parent = parent;
    }

    public AssoSimple() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void visitForDeclaration(GenerateurMetaJava g){
        g.generateDeclarationAttribut(this);
    }

    @Override
    public void visitForGetterSetter(GenerateurMetaJava g){
        g.generateGetterSetterAttribut(this);
    }

    @Override
    public void visitForParamConstructor(GenerateurMetaJava g){
        g.generateParamAttributForConstructor(this);
    }

    @Override
    public String toString() {
        return "AssoSimple{" +
                "value='" + value + '\'' +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", parent=" + parent +
                '}';
    }
}
