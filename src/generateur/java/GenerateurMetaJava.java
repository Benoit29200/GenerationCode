package generateur.java;

import modele.metamodele.*;
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
    private StringBuffer metaModeleJavaXML = new StringBuffer();
    private Parametres parametres;

    private GenerateurMetaJava() { }

    public void init(String filenameCode, String filenameParameter){
        mesModeles = ParserMetamodele.getInstance().parse(filenameCode);
        this.parametres = ParserParam.getInstance().parse(filenameParameter);
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

        generateImport(e, this.parametres.getTypes());

        // Génération déclaration attributs
        for(Association ass: e.getAssociations()){
            ass.visitForDeclaration(this);
        }
        // Génération constructeurs
        this.metaModeleJavaXML.append("\t\t\t<ConstructorEmpty name=\""+e.getNom()+"\"/>\n");
        this.metaModeleJavaXML.append("\t\t\t<ConstructorParams name=\""+e.getNom()+"\">\n");
        for(Association a: e.getAssociations()){
            a.visitForParamConstructor(this);
        }
        this.metaModeleJavaXML.append("\t\t\t</ConstructorParams>\n");


        // Génération getter et setter
        for(Association a: e.getAssociations()){
            a.visitForGetterSetter(this);
        }

        this.metaModeleJavaXML.append("\t\t </Class>\n");
    }


    public void generateDeclarationAttribut(AssoSimple ass){
        if(ass.getValue().equals("")){
            this.metaModeleJavaXML.append("\t\t\t<AssoSimple nom=\""+ass.getNom()+"\" type=\""+ass.getType()+"\"/>\n");
        }
        else{
            this.metaModeleJavaXML.append("\t\t\t<AssoSimple nom=\""+ass.getNom()+"\" type=\""+ass.getType()+"\" value=\'"+ass.getValue()+"\'/>\n");
        }
    }

    public void generateDeclarationCollection(Collection col){
        //TODO fait mais à tester
        this.metaModeleJavaXML.append("\t\t\t<Collection nom=\""+col.getNom()+"\" type=\""+this.getTypeJavaFromMinispec(col.getType())+"\" soustype=\""+col.getSoustype()+"\" cardMin=\""+col.getCardMin()+"\" cardMax=\""+col.getCardMax()+"\"/>\n");
    }

    public void generateDeclarationArray(Array ass){
        //TODO fait mais à tester
        this.metaModeleJavaXML.append("\t\t\t<Array nom=\""+ass.getNom()+"\" type=\""+ass.getType()+"\" cardMin=\""+ass.getCardMin()+"\" cardMax=\""+ass.getCardMax()+"\"/>\n");
    }

    public void generateGetterSetterAttribut(AssoSimple ass){
        this.metaModeleJavaXML.append("\t\t\t<GetterAttribut nom=\""+ass.getNom()+"\" type=\""+ass.getType()+"\"/>\n");
        this.metaModeleJavaXML.append("\t\t\t<SetterAttribut nom=\""+ass.getNom()+"\" type=\""+ass.getType()+"\"/>\n");
    }

    public void generateGetterSetterArray(Array array){
        this.metaModeleJavaXML.append("\t\t\t<GetterArray nom=\""+array.getNom()+"\" type=\""+array.getType()+"\"/>\n");
        this.metaModeleJavaXML.append("\t\t\t<SetterArray nom=\""+array.getNom()+"\" type=\""+array.getType()+"\"/>\n");
    }

    public void generateGetterSetterCollection(Collection col){
        this.metaModeleJavaXML.append("\t\t\t<GetterCollection nom=\""+col.getNom()+"\" type=\""+this.getTypeJavaFromMinispec(col.getType())+"\" soustype=\""+ col.getSoustype() +"\"/>\n");
        this.metaModeleJavaXML.append("\t\t\t<SetterGetterCollection nom=\""+col.getNom()+"\" type=\""+this.getTypeJavaFromMinispec(col.getType())+"\" soustype=\""+ col.getSoustype() +"\"/>\n");
    }

    public void generateParamAttributForConstructor(AssoSimple ass){
        this.metaModeleJavaXML.append("\t\t\t\t<ParamAttribut nom=\""+ass.getNom()+"\" type=\""+ass.getType()+"\"/>\n");
    }

    public void generateParamArrayForConstructor(Array array){
        this.metaModeleJavaXML.append("\t\t\t\t<ParamArray nom=\""+array.getNom()+"\" type=\""+array.getType()+"\"/>\n");
    }

    public void generateParamCollectionForConstructor(Collection col){
        this.metaModeleJavaXML.append("\t\t\t\t<ParamCollection nom=\""+col.getNom()+"\" type=\""+this.getTypeJavaFromMinispec(col.getType())+"\" soustype=\""+col.getSoustype()+"\"/>\n");
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

        Verificateur verificateur = Verificateur.getInstance();
        boolean errors = false;


        for(Modele modele: modeles){
            for(Entite e: modele.getEntites()){
                if(!verificateur.CirculariteOK(modele,e)){
                    System.err.println("> La classe \""+e.getNom()+"\" n'a pas été généré\n");
                    errors = true;
                }

                if(!verificateur.typeAttributOK(modeles,e.getAssociations(),this.parametres)){
                    errors = true;
                }
            }
        }

        if(errors) System.exit(0);
    }

    private void generateImport(Entite e, ArrayList<TypeParam> params){

        ArrayList<String> imports = new ArrayList<>();

        // si le type de l'attribut est un type collection du fichier de paramètrage, on l'ajoute dans les imports
        for(Association a: e.getAssociations()){
            for(TypeParam type: params){
                if(a.getType().equals(type.getNom()) && !imports.contains(type.getLePackage()) && !type.getLePackage().isEmpty()){
                    imports.add(type.getLePackage());
                }
            }
        }

        // si le type de l'attribut est une classe définie dans l'un de nos package, alors on ajoute cette classe en import
        for(Association a: e.getAssociations()){
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

    private String getTypeJavaFromMinispec(String type){
        for(TypeParam collection: this.parametres.getTypes()){
            if(collection.getNom().equals(type)){
                return collection.getType();
            }
        }
        return type;
    }
}
