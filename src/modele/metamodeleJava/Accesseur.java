package modele.metamodeleJava;

public abstract class Accesseur {

    protected String nom;
    protected String type;

    public abstract String getNom();
    public abstract String getType();

    public abstract void setNom(String nom);
    public abstract void setType(String type);
}
