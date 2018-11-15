package metamodele.attribut;

import com.sun.xml.internal.ws.util.StringUtils;
import metamodele.Attribut;
import metamodele.Entite;

public class AssociationSimple extends Attribut {

    private Entite type;

    public AssociationSimple(String nom, Entite type) {
        this.nom = nom;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public String getterSetterToJava(){
        return getter()+setter();
    }

    private String getter(){
        if(this.type.getNom().equals("Boolean")) {
            return"public " + this.type.getNom() + " " + StringUtils.capitalize(this.nom) + "() { return this." + this.nom + "; }";
        }else{
            return "public "+this.type.getNom()+" get"+ StringUtils.capitalize(this.nom)+"() { return this."+this.nom+"; }";
        }
    }

    private String setter(){
        return "public void set" + StringUtils.capitalize(this.nom) + "("+this.type.getNom()+" "+this.nom+") { this." + this.nom + " = "+this.nom+"; }";
    }

    public String DefinitionToJava(){
        return this.type.getNom()+" "+this.nom+";";
    }


    public String acceptEntiteForDefinition(Entite e){
        return e.definitionAssociationSimpleToJava(this);
    }

    public String acceptEntiteForGetterSetter(Entite e){
        return e.getterSetterAssociationSimpleToJava(this);
    }

    public void acceptEntiteForUnresolvedAttribut(Entite e){}

}
