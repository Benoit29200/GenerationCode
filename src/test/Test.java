package test;

import generateur.Generateur;

public class Test {

    public static void main(String[] args) {
        Generateur.getInstance().generate("code.xml","GenerateFolder");
    }
}
