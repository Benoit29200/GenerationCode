package test;

import generateur.GenerateurCodeJava;
import generateur.GenerateurMetaJava;
import modele.metamodele.Modele;
import parser.parserMetamodele.ParserMetamodele;

public class Test {

    public static void main(String[] args) {

        Modele p = ParserMetamodele.getInstance().parse("code.xml","parametrage.xml");

        GenerateurMetaJava generateurMetaJava = GenerateurMetaJava.getInstance();
        generateurMetaJava.init("code.xml", "parametrage.xml");
        generateurMetaJava.generate("metaModeleForJava.xml");

        GenerateurCodeJava generateurCodeJava = GenerateurCodeJava.getInstance();
        generateurCodeJava.init("metaModeleForJava.xml", "parametrage.xml");
        generateurCodeJava.generate();

        //Generateur.getInstance().generate("code.xml","parametrage.xml","src");
    }
}
