package metamodele;


import com.sun.xml.internal.ws.util.StringUtils;

public class Attribut {

    private String nom;
    private String type;

    public Attribut(String nom, String type) {
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
        return e.definitionAttributToJava(this);
    }

    public String acceptEntiteForGetterSetter(Entite e){
        return e.getterSetterAttributToJava(this);
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

}
