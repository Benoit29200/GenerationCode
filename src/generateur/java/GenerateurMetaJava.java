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

    private Modele myModele;
    private String metaModeleJavaXML;
    private String filenameParameter;

    private GenerateurMetaJava() {
        this.metaModeleJavaXML="";
    }

    public void init(String filenameCode, String filenameParameter){
        myModele = ParserMetamodele.getInstance().parse(filenameCode,filenameParameter);
        this.filenameParameter = filenameParameter;
    }

    public void generate(String filenameDest){

        this.verifications();

        metaModeleJavaXML += "<Code>\n";
        metaModeleJavaXML += "\t<Package nom=\""+this.myModele.getNom()+"\">\n";

        for(Entite e : this.myModele.getEntites()){
            e.visit(this);
        }

        metaModeleJavaXML += "\t</Package>\n";
        metaModeleJavaXML += "</Code>\n";

        this.ecritureFichier(filenameDest);

    }

    // Méthode appelé par le visiteur des entités
    public void generateEntity(Entite e){

        if(e.getSubtypeof().isEmpty()){
            this.metaModeleJavaXML += "\t\t<Class nom=\""+e.getNom()+"\">\n";
        } else{
            this.metaModeleJavaXML += "\t\t<Class nom=\""+e.getNom()+"\" subtypeof=\""+e.getSubtypeof()+"\">\n";
        }

        generateImport(e, ParserParam.getInstance().parse(this.filenameParameter).getTypes());

        // Génération déclaration attributs
        for(Attribut a: e.getAttributs()){
            a.visitForDeclaration(this);
        }
        // Génération constructeurs
        this.metaModeleJavaXML += "\t\t\t<ConstructorEmpty name=\""+e.getNom()+"\"/>\n";
        this.metaModeleJavaXML += "\t\t\t<ConstructorParams name=\""+e.getNom()+"\">\n";
        for(Attribut a: e.getAttributs()){
            a.visitForParamConstructor(this);
        }
        this.metaModeleJavaXML += "\t\t\t</ConstructorParams>\n";


        // Génération getter et setter
        for(Attribut a: e.getAttributs()){
            a.visitForGetterSetter(this);
        }

        this.metaModeleJavaXML += "\t\t </Class>\n";
    }


    public void generateDeclarationAttribut(Attribut a){
        this.metaModeleJavaXML += "\t\t\t<Attribut nom=\""+a.getNom()+"\" type=\""+a.getType()+"\"/>\n";

    }

    public void generateGetterSetterAttribut(Attribut a){
        this.metaModeleJavaXML += "\t\t\t<Getter nom=\""+a.getNom()+"\" type=\""+a.getType()+"\"/>\n";
        this.metaModeleJavaXML += "\t\t\t<Setter nom=\""+a.getNom()+"\" type=\""+a.getType()+"\"/>\n";
    }

    public void generateParamForConstructor(Attribut a){
        this.metaModeleJavaXML += "\t\t\t\t<Param nom=\""+a.getNom()+"\" type=\""+a.getType()+"\"/>\n";
    }

    private void ecritureFichier(String filenameDest){

        try{
            FileWriter fichier = new FileWriter(filenameDest);
            fichier.write (this.metaModeleJavaXML);
            fichier.close();
        }catch(Exception e){
            System.err.println("Problème écriture du fichier métaModeleJava");
        }

    }

    private void verifications(){

        Parametres lesParametres = ParserParam.getInstance().parse(this.filenameParameter);
        Verificateur verificateur = Verificateur.getInstance();
        boolean errors = false;


        for(Entite e: this.myModele.getEntites()){
            if(!verificateur.CirculariteOK(this.myModele,e)){
                System.err.println("> La classe \""+e.getNom()+"\" n'a pas été généré\n");
                errors = true;
            }

                if(!verificateur.typeAttributOK(this.myModele,e.getAttributs(),lesParametres)){
                    errors = true;
                }
        }

        if(errors) System.exit(0);
    }

    private void generateImport(Entite e, ArrayList<TypeParam> params){

        ArrayList<String> imports = new ArrayList<>();

        for(Attribut a: e.getAttributs()){
            for(TypeParam type: params){
                if(a.getType().equals(type.getNom()) && !imports.contains(type.getLePackage())){
                    imports.add(type.getLePackage());
                }
            }
        }

        for(String importPackage: imports){
            this.metaModeleJavaXML += "\t\t\t<Import nom=\""+importPackage+"\"/>\n";
        }
    }


}
