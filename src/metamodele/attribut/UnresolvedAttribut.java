package metamodele.attribut;

import metamodele.Attribut;
import metamodele.Entite;

public class UnresolvedAttribut extends Attribut {

    private String type;

    public UnresolvedAttribut(String nom, String type) {
        this.nom = nom;
        this.type = type;
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

    public String acceptEntiteForDefinition(Entite e){
        System.err.println("L'attribut nom:'"+this.nom+"' de type :'"+this.type+"' est incorrect");
        return null;
    }

    public String acceptEntiteForGetterSetter(Entite e){
        return "";
    }

    public void acceptEntiteForUnresolvedAttribut(Entite e){
        e.tryToResolveUnresolvedAttribut(this);
    }
}
