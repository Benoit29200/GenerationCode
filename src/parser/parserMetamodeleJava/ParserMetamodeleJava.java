package parser.parserMetamodeleJava;

import modele.metamodeleJava.*;

import modele.metamodeleJava.Class;
import modele.metamodeleJava.Package;
import modele.metamodeleJava.accesseur.getter.GetterArray;
import modele.metamodeleJava.accesseur.getter.GetterAttribut;
import modele.metamodeleJava.accesseur.getter.GetterCollection;
import modele.metamodeleJava.accesseur.setter.SetterArray;
import modele.metamodeleJava.accesseur.setter.SetterAttribut;
import modele.metamodeleJava.accesseur.setter.SetterCollection;
import modele.metamodeleJava.association.AssoSimple;
import modele.metamodeleJava.association.associationMultiple.Array;
import modele.metamodeleJava.association.associationMultiple.Collection;
import modele.metamodeleJava.constructor.ConstructorEmpty;
import modele.metamodeleJava.constructor.ConstructorParams;
import modele.metamodeleJava.param.ParamArray;
import modele.metamodeleJava.param.ParamAttribut;
import modele.metamodeleJava.param.ParamCollection;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
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


    public ArrayList<Package> parse(String filename) {

        try {
            final org.w3c.dom.Element racine = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename)).getDocumentElement();
            parseCode(racine);
            return this.mesPackages;

        } catch (Exception e) {
            System.err.println("Erreur lors du parsage du document xml");
        }
        return null;
    }


    private void parseCode(org.w3c.dom.Element node) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    if (elem.getNodeName().equals("Package")) {
                            String nomPackage = elem.getAttribute("nom");
                            Package g = new Package(nomPackage);
                            this.mesPackages.add(g);
                            parsePackage(elem, g);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse package :" + e.getMessage());
        }
    }

    private void parsePackage(org.w3c.dom.Element node, Package parent) {
        try {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    if (elem.getNodeName().equals("Class")) {
                            String nomClass = elem.getAttribute("nom");
                            String subtypeof = elem.getAttribute("subtypeof");
                            Class classe = new Class(nomClass, subtypeof);
                            parent.getClasses().add(classe);
                            parseClass(elem,classe);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse entite : " + e.getMessage());
        }
    }

    private void parseClass(org.w3c.dom.Element node, Class parent) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()) {

                        case "Import": {
                            String nomImport = elem.getAttribute("nom");
                            Import anImport = new Import(nomImport);
                            parent.getImports().add(anImport);
                            break;
                        }

                        case "AssoSimple": {
                            String nomAttribut = elem.getAttribute("nom");
                            String maValeur = elem.getAttribute("value");
                            String typeAttribut = elem.getAttribute("type");
                            AssoSimple assoSimple = new AssoSimple(nomAttribut,typeAttribut, maValeur, parent);
                            parent.getAssociations().add(assoSimple);

                            break;
                        }

                        case "Collection": {

                            String nomCollection = elem.getAttribute("nom");
                            String typeCollection = elem.getAttribute("type");
                            String soustypeCollection = elem.getAttribute("soustype");
                            int cardMin = Integer.parseInt(elem.getAttribute("cardMin"));
                            int cardMax = Integer.parseInt(elem.getAttribute("cardMax"));
                            Collection collection = new Collection(nomCollection,typeCollection,soustypeCollection,parent,cardMin,cardMax);
                            parent.getAssociations().add(collection);
                            break;
                        }

                        case "Array": {
                            String nomArray = elem.getAttribute("nom");
                            String typeArray = elem.getAttribute("type");
                            int cardMin = Integer.parseInt(elem.getAttribute("cardMin"));
                            int cardMax = Integer.parseInt(elem.getAttribute("cardMax"));
                            Array array = new Array(nomArray,typeArray,parent, cardMin,cardMax);
                            parent.getAssociations().add(array);

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
                            parseConstructorParams(elem,constructor);
                            break;
                        }

                        case "GetterAttribut": {
                            String nomGetterAttribut = elem.getAttribute("nom");
                            String typeGetterAttribut = elem.getAttribute("type");
                            GetterAttribut getterAttribut = new GetterAttribut(nomGetterAttribut,typeGetterAttribut);
                            parent.getAccesseurs().add(getterAttribut);

                            break;
                        }

                        case "GetterCollection": {
                            String nomGetterCollection = elem.getAttribute("nom");
                            String typeGetterCollection= elem.getAttribute("type");
                            String soustypeGetterCollection = elem.getAttribute("soustype");
                            GetterCollection getterCollection = new GetterCollection(nomGetterCollection,typeGetterCollection,soustypeGetterCollection);
                            parent.getAccesseurs().add(getterCollection);
                            break;
                        }

                        case "GetterArray": {
                            String nomGetterArray = elem.getAttribute("nom");
                            String typeGetterArray = elem.getAttribute("type");
                            GetterArray getterArray = new GetterArray(nomGetterArray,typeGetterArray);
                            parent.getAccesseurs().add(getterArray);
                            break;
                        }

                        case "SetterAttribut": {
                            String nomSetterAttribut = elem.getAttribute("nom");
                            String typeSetterAttribut = elem.getAttribute("type");
                            SetterAttribut setterAttribut = new SetterAttribut(nomSetterAttribut,typeSetterAttribut);
                            parent.getAccesseurs().add(setterAttribut);
                            break;
                        }
                        case "SetterCollection": {
                            String nomSetterCollection = elem.getAttribute("nom");
                            String typeSetterCollection= elem.getAttribute("type");
                            String soustypeSetterCollection = elem.getAttribute("soustype");
                            SetterCollection setterCollection = new SetterCollection(nomSetterCollection,typeSetterCollection,soustypeSetterCollection);
                            parent.getAccesseurs().add(setterCollection);
                            break;
                        }

                        case "SetterArray": {
                            String nomSetterArray = elem.getAttribute("nom");
                            String typeSetterArray = elem.getAttribute("type");
                            SetterArray setterArray = new SetterArray(nomSetterArray,typeSetterArray);
                            parent.getAccesseurs().add(setterArray);
                            break;
                        }

                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse attribut : " + e.getMessage());
        }
    }

    private void parseConstructorParams(org.w3c.dom.Element node, ConstructorParams parent) {
        try {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()) {

                        case "ParamAttribut": {
                            String nomParamAttribut = elem.getAttribute("nom");
                            String typeParamAttribut = elem.getAttribute("type");
                            ParamAttribut paramAttribut = new ParamAttribut(nomParamAttribut,typeParamAttribut);
                            parent.getParams().add(paramAttribut);
                            break;
                        }

                        case "ParamCollection": {
                            String nomParamCollection = elem.getAttribute("nom");
                            String typeParamCollection = elem.getAttribute("type");
                            String soustypeParamCollection = elem.getAttribute("soustype");
                            ParamCollection paramCollection = new ParamCollection(nomParamCollection,typeParamCollection,soustypeParamCollection);
                            parent.getParams().add(paramCollection);
                            break;
                        }

                        case "ParamArray": {
                            String nomParamArray = elem.getAttribute("nom");
                            String typeParamArray = elem.getAttribute("type");
                            ParamArray paramArray = new ParamArray(nomParamArray,typeParamArray);
                            parent.getParams().add(paramArray);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse constructor param : " + e.getMessage());
        }
    }
}
