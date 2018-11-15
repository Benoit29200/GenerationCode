package parser;

import metamodele.Attribut;
import metamodele.Entite;
import metamodele.Modele;
import metamodele.attribut.Primitive;
import metamodele.attribut.UnresolvedAttribut;
import org.w3c.dom.Node;
import verification.Verificateur;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Parser {

    private static Parser instance;
    private Modele modele;

    private Parser(){
        modele = new Modele();
    }

    public static Parser getInstance(){
        if(instance==null) instance = new Parser();
        return instance;
    }


    public Modele parse(String filename,String parametrageFilename) {

        try {
            final org.w3c.dom.Element racine = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename)).getDocumentElement();
            parseModele(racine,parametrageFilename);
            return this.modele;

        } catch (Exception e) {
            System.err.println("Erreur lors du parsage du document xml");
        }
        return null;
    }


    private void parseModele(org.w3c.dom.Element node, String parametrageFilename) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()){
                        case "Modele":{
                            String nomModele = elem.getAttribute("nom");
                            this.modele = new Modele(nomModele);
                            parseEntite(elem,this.modele,parametrageFilename);
                        break;
                        }
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("erreur parse modele :"+e.getMessage());
        }
    }

    private void parseEntite(org.w3c.dom.Element node,Modele parent,String parametrageFilename) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()){
                        case "Entite":{
                            String subtypeof = elem.getAttribute("subtypeof");
                            String nomEntite = elem.getAttribute("nom");
                            Entite monEntite = new Entite(nomEntite,this.modele,subtypeof);
                            parent.getEntites().add(monEntite);
                            parseAttribut(elem,monEntite,parametrageFilename);

                            break;
                        }
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("erreur parse entite : "+e.getMessage());
        }
    }

    private void parseAttribut(org.w3c.dom.Element node,Entite parent,String parametrageFilename) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()){
                        case "Attribut":{
                            String nomAttribut = elem.getAttribute("nom");
                            String typeAttribut = elem.getAttribute("type");

                            if(Verificateur.getInstance().isPrimitive(typeAttribut,parametrageFilename)){
                                Attribut monAttribut = new Primitive(nomAttribut,typeAttribut);
                                parent.getAttributs().add(monAttribut);
                            }
                            else{
                                // Création d'un UnresolvedAttribut
                                UnresolvedAttribut attributNonConnu = new UnresolvedAttribut(nomAttribut,typeAttribut);
                                parent.getAttributs().add(attributNonConnu);
                            }
                            break;
                        }
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("erreur parse attribut : "+e.getMessage());
        }
    }





}
