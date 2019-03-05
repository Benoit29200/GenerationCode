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
        this.generateAddddAndGetter();
        this.generateSerialize();
        this.generateDeserialize();
        this.generateClear();
        code.append("}");


        this.ecritureFichier();
    }

    private void generateImport(){
        code.append("package repository;\n\n");

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
        code.append("private HashMap<String, Object> classHashMap;\n")
            .append("private HashMap<String, Class> instanceHashMap;\n");
    }

    private void generateInstanceAndConstructor(){
        code.append("private static ").append(this.name).append(" instance;\n\n");
        code.append("private ").append(this.name).append(" (){\n")
            .append("this.classHashMap = new HashMap<>();\n")
            .append("this.instanceHashMap = new HashMap<>();\n");

        for(Package p:this.packages) {
            for (Class c : p.getClasses()) {
                code.append("this.instanceHashMap.put(\""+ c.getNom() +"\", "+c.getNom()+".class);\n");
            }
        }

        code.append("}\n")
            .append("public static ")
            .append(this.name)
            .append(" getInstance(){\n")
            .append("if(instance==null) instance = new ")
            .append(this.name)
            .append(" ();\n")
            .append("return instance;\n")
            .append("}\n");
    }


    private void generateSerialize(){
        code.append("public void serialize(String filename){\n")
            .append("File XmlFile = new File(filename);\n")
            .append("StringBuilder builder = new StringBuilder();\n")
            .append("builder.append(\"<repository>\");")
            .append("for (Map.Entry<String,Object> e : classHashMap.entrySet()){")
            .append("builder.append(\"<\"+ e.getValue().getClass().getSimpleName()+\" id=\\\"\"+e.getKey()+\"\\\"/>\\n\");}\n");


        code.append("builder.append(\"</repository>\");")
            .append("try{\n")
            .append("FileWriter fichier = new FileWriter(filename);\n")
            .append("fichier.write (builder.toString());\n")
            .append("fichier.close();\n")
            .append("}catch(Exception e){\n")
            .append("System.err.println(\"Problème écriture du lot d'instance\");\n")
            .append("}\n")
            .append("}");
    }

    private void generateClear(){
        code.append("public void clear(){\n");
        code.append("this.classHashMap = new HashMap<>();\n");
        code.append("}");
    }

    private void generateAddddAndGetter(){
        code.append("public void add(String id, Object o){\n this.classHashMap.put(id,o);\n }\n public Object get(String id){ \nreturn this.classHashMap.get(id);\n }\n");
    }

    private void generateDeserialize(){


        code.append("public void parseRepository(String filename) {")
            .append("try {")
            .append("final org.w3c.dom.Element node = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename)).getDocumentElement();")

            .append("for (int i = 0; i < node.getChildNodes().getLength(); i++) {")
            .append("if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {")
            .append("final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);")

            .append("String id = elem.getAttribute(\"id\");\n")
            .append("this.classHashMap.put(id,this.instanceHashMap.get(elem.getNodeName()).newInstance());\n")
            .append("}")
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
