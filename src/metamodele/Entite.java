package metamodele;

import com.sun.xml.internal.ws.util.StringUtils;
import verification.Verificateur;

import java.util.ArrayList;

public class Entite {

    String nom;

    ArrayList<Attribut> attributs;

    Modele parent;

    String subtypeof="";

    public Entite(String nom,Modele parent,String subtypeof) {
        this.nom = StringUtils.capitalize(nom);
        this.attributs = new ArrayList<>();
        this.parent = parent;
        this.subtypeof = subtypeof;
    }

    public ArrayList<Attribut> getAttributs() {
        return attributs;
    }

    public String getNom() {
        return nom;
    }

    public Modele getParent() {
        return parent;
    }

    public String getSubtypeof() {
        return subtypeof;
    }

    public String generateCodeEntiteToJava(){

        String code="";
        code += this.packageEntite();
        code += this.entete();
        code += this.definitionAttribut();
        code += this.constructor();
        code += this.getterSetterAttribut();
        code += "}";
        return code;
    }


    private String packageEntite(){
        return "package "+this.parent.getNom()+";";
    }

    private String entete(){
        if(subtypeof != ""){
            if(!Verificateur.getInstance().SubtypeIsValid(this)){
                System.exit(0);
                return null;
            }
            else{
                return "public class "+this.nom+" extends "+this.subtypeof+" {";
            }
        }else{
            return "public class "+this.nom+" {";
        }
    }

    private String definitionAttribut(){
        String definitions="";
        for(Attribut element:attributs){
            definitions += element.acceptEntiteForDefinition(this);
        }
        return definitions;
    }

    private String constructor(){
        return "public "+ this.nom +"() { }";
    }

    private String getterSetterAttribut(){
        String getterSetter="";
        for(Attribut element:attributs){
            getterSetter += element.acceptEntiteForGetterSetter(this);
        }
        return getterSetter;
    }



    protected String definitionAttributToJava(Attribut attribut){
        return attribut.DefinitionToJava();
    }

    protected String getterSetterAttributToJava(Attribut attribut){
        return attribut.getterSetterToJava();
    }

}
