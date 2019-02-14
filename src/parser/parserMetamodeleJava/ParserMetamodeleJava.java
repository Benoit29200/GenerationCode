package parser.parserMetamodeleJava;

import modele.metamodeleJava.*;

import modele.metamodeleJava.Class;
import modele.metamodeleJava.Package;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ParserMetamodeleJava {

    private static ParserMetamodeleJava instance;
    private ArrayList<Package> mesPackages;

    private ParserMetamodeleJava() {
        mesPackages = new ArrayList<>();
    }

    public static ParserMetamodeleJava getInstance() {
        if (instance == null) instance = new ParserMetamodeleJava();
        return instance;
    }


    public ArrayList<Package> parse(String filename, String parametrageFilename) {

        try {
            final org.w3c.dom.Element racine = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename)).getDocumentElement();
            parseCode(racine, parametrageFilename);
            return this.mesPackages;

        } catch (Exception e) {
            System.err.println("Erreur lors du parsage du document xml");
        }
        return null;
    }


    private void parseCode(org.w3c.dom.Element node, String parametrageFilename) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()) {
                        case "Package": {
                            String nomPackage = elem.getAttribute("nom");
                            Package g = new Package(nomPackage);
                            this.mesPackages.add(g);
                            parsePackage(elem, g, parametrageFilename);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse package :" + e.getMessage());
        }
    }

    private void parsePackage(org.w3c.dom.Element node, Package parent, String parametrageFilename) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()) {
                        case "Class": {
                            String nomClass = elem.getAttribute("nom");
                            String subtypeof = elem.getAttribute("subtypeof");
                            Class classe = new Class(nomClass, subtypeof);
                            parent.getClasses().add(classe);
                            parseClass(elem,classe, parametrageFilename);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse entite : " + e.getMessage());
        }
    }

    private void parseClass(org.w3c.dom.Element node, Class parent, String parametrageFilename) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()) {
                        case "Attribut": {
                            String nomAttribut = elem.getAttribute("nom");
                            String maValeur = elem.getAttribute("value");
                            String typeAttribut = elem.getAttribute("type");
                            Attribut attribut = new Attribut(nomAttribut, typeAttribut, maValeur);
                            parent.getAttributs().add(attribut);
                            break;
                        }

                        case "Import": {
                            String nomImport = elem.getAttribute("nom");
                            Import anImport = new Import(nomImport);
                            parent.getImports().add(anImport);
                            break;
                        }

                        case "ConstructorEmpty": {
                            String nom = elem.getAttribute("name");
                            ConstructorEmpty constructor = new ConstructorEmpty(nom);
                            parent.getConstructors().add(constructor);
                            break;
                        }

                        case "ConstructorParams": {
                            String nom = elem.getAttribute("name");
                            ConstructorParams constructor = new ConstructorParams(nom);
                            parent.getConstructors().add(constructor);
                            parseConstructorParams(elem,constructor, parametrageFilename);
                            break;
                        }

                        case "Getter": {
                            String nom = elem.getAttribute("nom");
                            String type = elem.getAttribute("type");
                            Getter getter = new Getter(nom,type);
                            parent.getAccesseurs().add(getter);
                            break;
                        }
                        case "Setter": {
                            String nom = elem.getAttribute("nom");
                            String type = elem.getAttribute("type");
                            Setter setter = new Setter(nom,type);
                            parent.getAccesseurs().add(setter);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse attribut : " + e.getMessage());
        }
    }

    private void parseConstructorParams(org.w3c.dom.Element node, ConstructorParams parent, String parametrageFilename) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()) {
                        case "Param": {
                            String nom = elem.getAttribute("nom");
                            String type = elem.getAttribute("type");
                            Param parametre = new Param(nom,type);
                            parent.getParams().add(parametre);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse entite : " + e.getMessage());
        }
    }
}
