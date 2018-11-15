package metamodele;


public abstract class Attribut {

    protected String nom;
    public abstract String getNom();

    public abstract String acceptEntiteForDefinition(Entite e);
    public abstract String acceptEntiteForGetterSetter(Entite e);
    public abstract void acceptEntiteForUnresolvedAttribut(Entite e);

}
