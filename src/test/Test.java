package test;

import generateur.GenerateurMetaJava;
import modele.metamodele.Modele;
import modele.metamodeleJava.Package;
import parser.parserMetamodele.ParserMetamodele;
import parser.parserMetamodeleJava.ParserMetamodeleJava;

public class Test {

    public static void main(String[] args) {

        // Modele p = ParserMetamodele.getInstance().parse("code.xml","parametrage.xml");

        //GenerateurMetaJava generateurMetaJava = GenerateurMetaJava.getInstance();
        //generateurMetaJava.init("code.xml", "parametrage.xml");
        //generateurMetaJava.generate("metaModeleForJava.xml");

        Package monPackage = ParserMetamodeleJava.getInstance().parse("metaModeleForJava.xml", "parametrage.xml");
        System.out.println(monPackage);

        //Generateur.getInstance().generate("code.xml","parametrage.xml","src");
    }
}
