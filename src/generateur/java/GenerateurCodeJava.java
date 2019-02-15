package generateur.java;

import modele.metamodeleJava.*;
import modele.metamodeleJava.Class;
import modele.metamodeleJava.Package;
import modele.metamodeleJava.accesseur.getter.GetterArray;
import modele.metamodeleJava.accesseur.getter.GetterAttribut;
import modele.metamodeleJava.accesseur.getter.GetterCollection;
import modele.metamodeleJava.accesseur.setter.SetterArray;
import modele.metamodeleJava.accesseur.setter.SetterAttribut;
import modele.metamodeleJava.accesseur.setter.SetterCollection;
import modele.metamodeleJava.association.AssoSimple;
import modele.metamodeleJava.association.associationMultiple.Array;
import modele.metamodeleJava.association.associationMultiple.Collection;
import modele.metamodeleJava.constructor.ConstructorEmpty;
import modele.metamodeleJava.constructor.ConstructorParams;
import modele.metamodeleJava.param.ParamArray;
import modele.metamodeleJava.param.ParamAttribut;
import modele.metamodeleJava.param.ParamCollection;
import parser.parserMetamodeleJava.ParserMetamodeleJava;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class GenerateurCodeJava {
    private static GenerateurCodeJava ourInstance = new GenerateurCodeJava();

    public static GenerateurCodeJava getInstance() {
        return ourInstance;
    }

    private ArrayList<Package> mesPackages;
    private String filenameParameter;
    private StringBuffer code;

    private GenerateurCodeJava() {
        this.code = new StringBuffer();
    }

    public void init(String filenameMetaJava, String filenameParameter){
        this.mesPackages = ParserMetamodeleJava.getInstance().parse(filenameMetaJava);
        this.filenameParameter = filenameParameter;

        for(Package monPackage: mesPackages){
            // création du répertoire du package
            File directory = new File("src/"+monPackage.getNom());

            //création du répertoire du package
            boolean isCreated = directory.mkdirs();

            // si le répertoire n'est pas créé on quitte le programme
            if(!isCreated){
                System.err.println("Erreur à la création du répertoire du package "+monPackage.getNom()+".");
                System.exit(0);
            }
        }
    }
    // on génère chaque class du modèle
    public void generate(){
        for(Package monPackage: mesPackages){
            for(Class c: monPackage.getClasses()){
                this.generateCodeClass(c, monPackage);
            }
        }
    }

    private void generateCodeClass(Class c, Package monPackage){
        /**
         * 1- Package
         * 2- Import
         * 3- Entête de classe
         * 4- Attributs
         * 5- Constructeur
         * 6- accesseur
         *
         * 7- Création du fichier
         */

        // -- 1 --
        code.append("package " + monPackage.getNom() + ";\n\n");

        // -- 2 --
        for(Import anImport: c.getImports()){
            generateCodeImport(anImport,code);
        }

        // -- 3 --
        code.append("public class "+c.getNom()+" ");
        if(c.getSubtypeof().equals("")){
            code.append("{\n\n");
        }else {
            code.append("extends "+c.getSubtypeof()+"{\n\n");
        }

        // -- 4 --
        for(Association a: c.getAssociations()){
            // TODO appel visiteur pour générer le code en fonction de l'association
            a.visitForGenerateCodeAssociation(this);
        }

        // -- 5 --
        for(Constructor constructor: c.getConstructors()){
            generateCodeConstructor(constructor, code);
        }

        // -- 6 --
        for(Accesseur accesseur: c.getAccesseurs()){
            accesseur.visitForGenerateAccesseur(this);
        }
        code.append("\n}");

        // -- 7 --
        this.ecritureFichier(c, code, monPackage);
        this.code = new StringBuffer();
    }

    private void generateCodeImport(Import anImport, StringBuffer code){
        code.append("import "+anImport.getNom()+";\n");
    }

    // appelé par le visiteur de la clase AssoSimple.java
    public void generateCodeAttribut(AssoSimple a){
        if(a.getValue().equals("")){
            code.append("private "+a.getType() + " " + a.getNom() + ";\n");
        }
        else{
            code.append("private "+a.getType() + " " + a.getNom() + " = "+a.getValue()+";\n");
        }

    }

    public void generateCodeCollection(Collection col){
        code.append("private "+col.getType()+"<"+col.getSoustype()+"> "+col.getNom()+";\n");
    }

    public void generateCodeArray(Array array){
        code.append("private "+array.getType()+"[] "+array.getNom()+";\n");
    }

    private void generateCodeConstructor(Constructor c, StringBuffer code){
        c.generateCodeConstructor(this, code);
    }


    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public void generateCodeGetterAttribut(GetterAttribut g){
        code.append("public " + g.getType() + " get" + capitalize(g.getNom()) + "(){ return this." + g.getNom() +";}\n\n");
    }

    public void generateCodeGetterCollection(GetterCollection col){
        code.append("public " + col.getType()+"<"+col.getSoustype()+"> get" + capitalize(col.getNom()) + "(){ return this."+col.getNom()+";}\n\n");
    }

    public void generateCodeGetterArray(GetterArray array){
        code.append("public "+ array.getType()+"[] get"+ capitalize(array.getNom()) + "() { return this."+array.getNom()+";}\n\n");
    }

    // Appelée par le visiteur de SetterAttribut.java
    public void generateCodeSetterAttribut(SetterAttribut s){
        code.append("public void set"+capitalize(s.getNom()) +"("+s.getType()+" "+s.getNom()+"){this."+s.getNom()+"="+s.getNom()+";}\n\n");
    }

    public void generateCodeSetterCollection(SetterCollection col){
        code.append("public void set"+capitalize(col.getNom())+"("+col.getType()+"<"+col.getSoustype()+"> "+col.getNom()+"){this."+col.getNom()+"="+col.getNom()+";}");
    }

    public void generateCodeSetterArray(SetterArray array){
        code.append("public void set"+capitalize(array.getNom())+"("+array.getType()+"[] "+array.getNom()+"){this."+array.getNom()+"="+array.getNom()+";}");
    }

    // Appelée par le visiteur de ConstructorEmpty
    public void generateCodeConstructorEmpty(ConstructorEmpty constructorEmpty, StringBuffer code){
        code.append("public "+constructorEmpty.getNom()+"(){}\n\n");
    }

    // Appelée par le visiteur de ConstructorParams
    public void generateCodeConstructorParams(ConstructorParams constructorParams, StringBuffer code){
        code.append("public "+constructorParams.getNom()+"(");

        for(int i = 0 ; i < constructorParams.getParams().size()-1;i++){
            constructorParams.getParams().get(i).generateCodeEnteteConstructorParam(this);
            code.append(",");
        }
        constructorParams.getParams().get(constructorParams.getParams().size()-1).generateCodeEnteteConstructorParam(this);
        code.append("){");

        for(Param p: constructorParams.getParams()){
            code.append("this."+p.getNom()+"="+p.getNom()+";\n");
        }

        code.append("}\n");
    }

    public void generateCodeEnteteConstructorParamAttribut(ParamAttribut param){
        code.append(param.getType()+" "+param.getNom());
    }

    public void generateCodeEnteteConstructorParamCollection(ParamCollection param){
        code.append(param.getType()+"<"+param.getSoustype()+"> "+param.getNom());
    }

    public void generateCodeEnteteConstructorParamArray(ParamArray param){
        code.append(param.getType()+"[] "+param.getNom());
    }

    private void ecritureFichier(Class c, StringBuffer code, Package monPackage){

        try{
            FileWriter fichier = new FileWriter("src/"+monPackage.getNom()+"/"+c.getNom()+".java");
            fichier.write (code.toString());
            fichier.close();
        }catch(Exception e){
            System.err.println("Problème écriture du fichier métaModeleJava");
        }

    }

}
