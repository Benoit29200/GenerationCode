package test;

import generateur.Generateur;
import parametrage.Parametrage;
import parametrage.PrimitiveParametrage;

public class Test {

    public static void main(String[] args) {
        Generateur.getInstance().generate("code.xml","parametrage.xml","GenerateFolder");
    }
}
