package generateur.java;

import modele.metamodele.Attribut;
import modele.metamodele.Entite;
import modele.metamodele.Modele;
import modele.parametre.Parametres;
import parser.parserParametre.ParserParam;
import modele.parametre.TypeParam;
import parser.parserMetamodele.ParserMetamodele;
import verificateur.Verificateur;

import java.io.FileWriter;
import java.util.ArrayList;

public class GenerateurMetaJava {
    private static GenerateurMetaJava ourInstance = new GenerateurMetaJava();

    public static GenerateurMetaJava getInstance() {
        return ourInstance;
    }

    private ArrayList<Modele> mesModeles;
    StringBuffer metaModeleJavaXML = new StringBuffer("");
    private String filenameParameter;

    private GenerateurMetaJava() { }

    public void init(String filenameCode, String filenameParameter){
        mesModeles = ParserMetamodele.getInstance().parse(filenameCode,filenameParameter);
        this.filenameParameter = filenameParameter;
    }

    public void generate(String filenameDest){
        this.verifications(mesModeles);

        metaModeleJavaXML.append("<Code>\n");
        for(Modele myModele: this.mesModeles){
            metaModeleJavaXML.append("\t<Package nom=\""+myModele.getNom()+"\">\n");
            for(Entite e : myModele.getEntites()){
                e.visit(this);
            }
            metaModeleJavaXML.append("\t</Package>\n");
        }
        metaModeleJavaXML.append("</Code>\n");

        this.ecritureFichier(filenameDest);

    }

    // Méthode appelé par le visiteur des entités
    public void generateEntity(Entite e){

        if(e.getSubtypeof().isEmpty()){
            this.metaModeleJavaXML.append("\t\t<Class nom=\""+e.getNom()+"\">\n");
        } else{
            this.metaModeleJavaXML.append("\t\t<Class nom=\""+e.getNom()+"\" subtypeof=\""+e.getSubtypeof()+"\">\n");
        }

        generateImport(e, ParserParam.getInstance().parse(this.filenameParameter).getTypes());

        // Génération déclaration attributs
        for(Attribut a: e.getAttributs()){
            a.visitForDeclaration(this);
        }
        // Génération constructeurs
        this.metaModeleJavaXML.append("\t\t\t<ConstructorEmpty name=\""+e.getNom()+"\"/>\n");
        this.metaModeleJavaXML.append("\t\t\t<ConstructorParams name=\""+e.getNom()+"\">\n");
        for(Attribut a: e.getAttributs()){
            a.visitForParamConstructor(this);
        }
        this.metaModeleJavaXML.append("\t\t\t</ConstructorParams>\n");


        // Génération getter et setter
        for(Attribut a: e.getAttributs()){
            a.visitForGetterSetter(this);
        }

        this.metaModeleJavaXML.append("\t\t </Class>\n");
    }


    public void generateDeclarationAttribut(Attribut a){
        if(a.getValue().equals("")){
            this.metaModeleJavaXML.append("\t\t\t<Attribut nom=\""+a.getNom()+"\" type=\""+a.getType()+"\"/>\n");
        }
        else{
            this.metaModeleJavaXML.append("\t\t\t<Attribut nom=\""+a.getNom()+"\" type=\""+a.getType()+"\" value=\'"+a.getValue()+"\'/>\n");
        }


    }

    public void generateGetterSetterAttribut(Attribut a){
        this.metaModeleJavaXML.append("\t\t\t<Getter nom=\""+a.getNom()+"\" type=\""+a.getType()+"\"/>\n");
        this.metaModeleJavaXML.append("\t\t\t<Setter nom=\""+a.getNom()+"\" type=\""+a.getType()+"\"/>\n");
    }

    public void generateParamForConstructor(Attribut a){
        this.metaModeleJavaXML.append("\t\t\t\t<Param nom=\""+a.getNom()+"\" type=\""+a.getType()+"\"/>\n");
    }

    private void ecritureFichier(String filenameDest){

        try{
            FileWriter fichier = new FileWriter(filenameDest);
            fichier.write (this.metaModeleJavaXML.toString());
            fichier.close();
        }catch(Exception e){
            System.err.println("Problème écriture du fichier métaModeleJava");
        }
    }

    private void verifications(ArrayList<Modele> modeles){

        Parametres lesParametres = ParserParam.getInstance().parse(this.filenameParameter);
        Verificateur verificateur = Verificateur.getInstance();
        boolean errors = false;


        for(Modele modele: modeles){
            for(Entite e: modele.getEntites()){
                if(!verificateur.CirculariteOK(modele,e)){
                    System.err.println("> La classe \""+e.getNom()+"\" n'a pas été généré\n");
                    errors = true;
                }

                if(!verificateur.typeAttributOK(modeles,e.getAttributs(),lesParametres)){
                    errors = true;
                }
            }
        }

        if(errors) System.exit(0);
    }

    private void generateImport(Entite e, ArrayList<TypeParam> params){

        ArrayList<String> imports = new ArrayList<>();

        // si le type de l'attribut est un type collection du fichier de paramètrage, on l'ajoute dans les imports
        for(Attribut a: e.getAttributs()){
            for(TypeParam type: params){
                if(a.getType().equals(type.getNom()) && !imports.contains(type.getLePackage())){
                    imports.add(type.getLePackage());
                }
            }
        }

        // si le type de l'attribut est une classe définie dans l'un de nos package, alors on ajoute cette classe en import
        for(Attribut a: e.getAttributs()){
            for(Modele monPackage: this.mesModeles){
                for(Entite entite: monPackage.getEntites()){
                    if(a.getType().equals(entite.getNom())){
                        if(!a.getParent().getParent().getNom().equals(entite.getParent().getNom())){
                            imports.add(monPackage.getNom()+"."+entite.getNom());
                        }
                    }
                }
            }
        }

        // On génére le code pour chaque import
        for(String importPackage: imports){
            this.metaModeleJavaXML.append("\t\t\t<Import nom=\""+importPackage+"\"/>\n");
        }
    }
}
