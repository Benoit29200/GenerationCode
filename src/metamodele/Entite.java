package metamodele;

import com.sun.xml.internal.ws.util.StringUtils;
import verification.Verificateur;

import java.util.ArrayList;

public class Entite {

    String nom;

    ArrayList<Attribut> attributs;

    Modele parent;

    String codeJava="";

    String subtypeof;

    public Entite(String nom,Modele parent,String subtypeof) {
        this.nom = StringUtils.capitalize(nom);
        this.attributs = new ArrayList<>();
        this.parent = parent;
        this.subtypeof = subtypeof;
    }
//

    public ArrayList<Attribut> getAttributs() {
        return attributs;
    }
    public String getCodeJava() {
        return codeJava;
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

    public void generateCodeEntiteToJava(){

        codeJava += "package "+this.parent.getNom()+";";

        if(subtypeof != ""){
            if(!Verificateur.getInstance().SubtypeIsValid(this)){
                System.exit(0);
            }
            else{
                codeJava += "public class "+this.nom+" extends "+this.subtypeof+" {";
            }
        }else{
            codeJava += "public class "+this.nom+" {";
        }


        for(Attribut element:attributs){
           element.acceptEntiteForDefinition(this);
        }

        codeJava += generateConstructor();

        for(Attribut element:attributs){
            element.acceptEntiteForGetterSetter(this);
        }
        codeJava += "}";
    }


    private String generateConstructor(){
        return "public "+ this.nom +"() { }";
    }

    protected void definitionAttributToJava(Attribut attribut){
        codeJava += attribut.DefinitionToJava();
    }

    protected void getterSetterAttributToJava(Attribut attribut){
        codeJava += attribut.getterSetterToJava();
    }

}
