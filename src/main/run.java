package main;



import generateur.java.GenerateurCodeJava;
import generateur.java.GenerateurMetaJava;


public class run {

    public static void main(String[] args) {

        GenerateurMetaJava generateurMetaJava = GenerateurMetaJava.getInstance();
        generateurMetaJava.init("code.xml", "parametrage.xml");
        generateurMetaJava.generate("metaModeleForJava.xml");

        GenerateurCodeJava generateurCodeJava = GenerateurCodeJava.getInstance();
        if(generateurCodeJava.init("metaModeleForJava.xml", "parametrage.xml")){
            generateurCodeJava.generate("Repository");
        }





    }
}
