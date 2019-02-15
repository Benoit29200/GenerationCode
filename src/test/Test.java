package test;

import generateur.java.GenerateurCodeJava;
import generateur.java.GenerateurMetaJava;

public class Test {

    public static void main(String[] args) {

        GenerateurMetaJava generateurMetaJava = GenerateurMetaJava.getInstance();
        generateurMetaJava.init("code.xml", "parametrage.xml");
        generateurMetaJava.generate("metaModeleForJava.xml");

        GenerateurCodeJava generateurCodeJava = GenerateurCodeJava.getInstance();
        generateurCodeJava.init("metaModeleForJava.xml", "parametrage.xml");
        generateurCodeJava.generate();

    }
}
