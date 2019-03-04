package generateur.java;

import modele.metamodeleJava.Class;
import modele.metamodeleJava.Package;

import java.io.FileWriter;
import java.util.ArrayList;

public class GenerateurRepository {
    private static GenerateurRepository ourInstance = new GenerateurRepository();

    public static GenerateurRepository getInstance() {
        return ourInstance;
    }

    private ArrayList<Package> packages;
    private String name;
    private StringBuilder code = new StringBuilder();

    private GenerateurRepository() {
        packages = new ArrayList<>();
    }

    public void init(ArrayList<Package> packages, String nameRepository){
        this.packages = packages;
        this.name = nameRepository;

        this.generateImport();
        this.generateHashMap();
        this.generateInstanceAndConstructor();
        this.generateGuetter();
        this.generateMethodeAdd();
        this.generateSerialize();
        this.generateDeserialize();
        code.append("}");


        this.ecritureFichier();
    }

    private void generateImport(){
        code.append("package Repository;\n\n");

        for(Package p: this.packages){
            for(Class c: p.getClasses()){
                code.append("import ")
                        .append(p.getNom())
                        .append(".")
                        .append(c.getNom())
                        .append(";\n");
            }
            code.append("\n");
        }

        code.append("import java.io.File;\n")
        .append("import java.io.FileWriter;\n")
        .append("import java.util.HashMap;\n")
        .append("import java.util.Map;\n")
        .append("import org.w3c.dom.Node;\n")
        .append("import javax.xml.parsers.DocumentBuilderFactory;\n")
        .append("public class ")
        .append(this.name)
        .append(" {\n");
    }

    private void generateHashMap(){

        for(Package p:this.packages){
            for(Class c: p.getClasses()){
                code.append("private HashMap<String, ")
                    .append(c.getNom())
                    .append("> ")
                    .append(c.getNom().toLowerCase())
                    .append("HashMap")
                    .append(";\n");
            }
            code.append("\n");
        }
    }

    private void generateInstanceAndConstructor(){
        code.append("private static ")
            .append(this.name)
            .append(" instance;\n\n");

    code.append("private ")
        .append(this.name)
        .append(" (){\n");

        for(Package p:this.packages){
            for(Class c: p.getClasses()){
                code.append("this.")
                    .append(c.getNom().toLowerCase())
                    .append("HashMap")
                    .append(" =  new HashMap<>();\n");
            }
        }

        code.append("}\n");

    code.append("public static ")
        .append(this.name)
        .append(" getInstance(){\n");
    code.append("if(instance==null) instance = new ")
        .append(this.name)
        .append(" ();\n");
    code.append("return instance;\n");
    code.append("}\n");
    }

    private void generateGuetter(){

        for(Package p:this.packages) {
            for (Class c : p.getClasses()) {
                code.append("public HashMap<String,")
                    .append(c.getNom())
                    .append("> get"+c.getNom()+"HashMap(){ return this."+ c.getNom().toLowerCase() +"HashMap; }\n");
            }
        }
    }

    private void generateMethodeAdd(){
        for(Package p: this.packages){
            for(Class c: p.getClasses()){
                code.append("public void add"+c.getNom()+"(String key, "+c.getNom()+" value){");
                    code.append("this."+c.getNom().toLowerCase()+"HashMap.put(key,value);}\n");
            }
        }
    }

    private void generateSerialize(){
        code.append("public void serialize(String filename){\n");
        code.append("File XmlFile = new File(filename);\n");
        code.append("StringBuilder builder = new StringBuilder();\n");

        for(Package p:this.packages) {
            for (Class c : p.getClasses()) {
                code.append("for (Map.Entry<String,"+c.getNom()+"> e : "+c.getNom().toLowerCase()+"HashMap.entrySet()){");
                code.append("builder.append(\"<"+c.getNom()+" id=\\\"\"+e.getKey()+\"\\\">\\n\");}\n");
            }
        }

        code.append("try{\n");
        code.append("FileWriter fichier = new FileWriter(filename);\n");
        code.append("fichier.write (builder.toString());\n");
        code.append("fichier.close();\n");
        code.append("}catch(Exception e){\n");
        code.append("System.err.println(\"Problème écriture du lot d'instance\");\n");
        code.append("}\n");


        code.append("}");
    }

    private void generateDeserialize(){


        code.append("private void parseRepository(String filename) {")
        .append("try {")
        .append("final org.w3c.dom.Element node = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename)).getDocumentElement();")

        .append("for (int i = 0; i < node.getChildNodes().getLength(); i++) {")
        .append("if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {")
        .append("final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);");


        for(Package p:this.packages) {
            for (Class c : p.getClasses()) {


                code.append("if (elem.getNodeName().equals(\""+c.getNom()+"\")) {");
                    code.append("String id = elem.getAttribute(\"id\");");
                    code.append(c.getNom()+ " "+c.getNom().toLowerCase()+" = new "+c.getNom()+"();");
                    code.append("this.add"+c.getNom()+"(id,"+c.getNom().toLowerCase()+");");

                code.append("}");



            }
        }

        code.append("}")
        .append("}")
        .append("} catch (Exception e) {")
        .append("System.out.println(\"erreur parsing repository :\" + e.getMessage());")
        .append("}")
        .append("}");
    }

    private void ecritureFichier(){

        try{
            FileWriter fichier = new FileWriter("src/repository/"+this.name+".java");
            fichier.write (this.code.toString());
            fichier.close();
        }catch(Exception e){
            System.err.println("Problème écriture du repository");
        }
    }
}
