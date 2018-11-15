package metamodele.attribut;

import com.sun.xml.internal.ws.util.StringUtils;
import metamodele.Attribut;
import metamodele.Entite;

public class Primitive extends Attribut {

    private String type;

    public Primitive(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public String DefinitionToJava(){
        return this.type+" "+this.nom+";";
    }

    public String getterSetterToJava(){
        return getter()+setter();
    }


    public String acceptEntiteForDefinition(Entite e){
        return e.definitionPrimitiveToJava(this);
    }

    public String acceptEntiteForGetterSetter(Entite e){
        return e.getterSetterPrimitiveToJava(this);
    }

    private String getter(){
        if(this.type.equals("Boolean")) {
            return"public " + this.type + " " + StringUtils.capitalize(this.nom) + "() { return this." + this.nom + "; }";
        }else{
            return "public "+this.type+" get"+ StringUtils.capitalize(this.nom)+"() { return this."+this.nom+"; }";
        }
    }

    private String setter(){
        return "public void set" + StringUtils.capitalize(this.nom) + "("+this.type+" "+this.nom+") { this." + this.nom + " = "+this.nom+"; }";
    }

    public void acceptEntiteForUnresolvedAttribut(Entite e){
        return;
    }
}
