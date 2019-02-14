package generateur.java;

import modele.metamodeleJava.Attribut;
import modele.metamodeleJava.*;
import modele.metamodeleJava.Class;
import modele.metamodeleJava.Package;
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
    String filenameParameter;

    private GenerateurCodeJava() {
    }

    public void init(String filenameMetaJava, String filenameParameter){
        this.mesPackages = ParserMetamodeleJava.getInstance().parse(filenameMetaJava, filenameParameter);
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
        StringBuffer code = new StringBuffer("");
        /**
         * 1- Package
         * 2- Import
         * 3- Entête de classe
         * 4- Attributs
         * 5- Constructeur
         * 6- Accesseur
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
        for(Attribut a: c.getAttributs()){
            generateCodeAttribut(a, code);
        }

        // -- 5 --
        for(Constructor constructor: c.getConstructors()){
            generateCodeConstructor(constructor, code);
        }

        // -- 6 --
        for(Accesseur accesseur: c.getAccesseurs()){
            generateCodeAccesseur(accesseur, code);
        }
        code.append("\n}");

        // -- 7 --
        this.ecritureFichier(c, code, monPackage);
    }

    private void generateCodeImport(Import anImport, StringBuffer code){
        code.append("import "+anImport.getNom()+";\n");
    }

    private void generateCodeAttribut(Attribut a, StringBuffer code){
        if(a.getValue().equals("")){
            code.append("private "+a.getType() + " " + a.getNom() + ";\n");
        }
        else{
            code.append("private "+a.getType() + " " + a.getNom() + " = "+a.getValue()+";\n");
        }

    }

    private void generateCodeConstructor(Constructor c, StringBuffer code){
        c.generateCodeConstructor(this, code);
    }

    private void generateCodeAccesseur(Accesseur accesseur, StringBuffer code){
        accesseur.generateAccesseur(this,code);
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    // Appelée par le visiteur de Getter
    public void generateCodeGetter(Getter g, StringBuffer code){
        code.append("public " + g.getType() + " get" + capitalize(g.getNom()) + "(){ return this." + g.getNom() +";}\n\n");
    }

    // Appelée par le visiteur de Setter
    public void generateCodeSetter(Setter s, StringBuffer code){
        code.append("public void set"+capitalize(s.getNom()) +"("+s.getType()+" "+s.getNom()+"){this."+s.getNom()+"="+s.getNom()+";}\n\n");
    }

    // Appelée par le visiteur de ConstructorEmpty
    public void generateCodeConstructorEmpty(ConstructorEmpty constructorEmpty, StringBuffer code){
        code.append("public "+constructorEmpty.getNom()+"(){}\n\n");
    }

    // Appelée par le visiteur de ConstructorParams
    public void generateCodeConstructorParams(ConstructorParams constructorParams, StringBuffer code){
        code.append("public "+constructorParams.getNom()+"(");

        for(int i = 0 ; i < constructorParams.getParams().size()-1;i++){
            code.append(constructorParams.getParams().get(i).getType()+" "+constructorParams.getParams().get(i).getNom()+",");
        }
        code.append(constructorParams.getParams().get(constructorParams.getParams().size()-1).getType()+" "+constructorParams.getParams().get(constructorParams.getParams().size()-1).getNom());
        code.append("){");

        for(Param p: constructorParams.getParams()){
            code.append("this."+p.getNom()+"="+p.getNom()+";\n");
        }

        code.append("}\n");
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
