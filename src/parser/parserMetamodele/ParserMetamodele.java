package parser.parserMetamodele;

import modele.metamodele.Attribut;
import modele.metamodele.Entite;
import modele.metamodele.Modele;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class ParserMetamodele {

    private static ParserMetamodele instance;
    private ArrayList<Modele> mesModeles;

    private ParserMetamodele() {
        mesModeles = new ArrayList<>();
    }

    public static ParserMetamodele getInstance() {
        if (instance == null) instance = new ParserMetamodele();
        return instance;
    }


    public ArrayList<Modele> parse(String filename) {

        try {
            final org.w3c.dom.Element racine = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename)).getDocumentElement();
            parsePackage(racine);
            return this.mesModeles;

        } catch (Exception e) {
            System.err.println("Erreur lors du parsage du document xml");
        }
        return null;
    }


    private void parsePackage(org.w3c.dom.Element node) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    if (elem.getNodeName().equals("Modele")) {
                            String nomPackage = elem.getAttribute("nom");
                            Modele m = new Modele(nomPackage);
                            this.mesModeles.add(m);
                            parseEntite(elem, m);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse modele :" + e.getMessage());
        }
    }

    private void parseEntite(org.w3c.dom.Element node, Modele parent) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    if (elem.getNodeName().equals("Entite")) {
                            String subtypeof = elem.getAttribute("subtypeof");
                            String nomEntite = elem.getAttribute("nom");
                            Entite monEntite = new Entite(nomEntite, subtypeof, parent);
                            parent.getEntites().add(monEntite);
                            parseAttribut(elem, monEntite);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse entite : " + e.getMessage());
        }
    }

    private void parseAttribut(org.w3c.dom.Element node, Entite parent) {
        try {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    if (elem.getNodeName().equals("Attribut")) {
                            String nomAttribut = elem.getAttribute("nom");
                            String typeAttribut = elem.getAttribute("type");
                            String value = elem.getAttribute("value");
                            Attribut p = new Attribut(nomAttribut,typeAttribut, value, parent);
                            parent.addAttribut(p);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse attribut : " + e.getMessage());
        }
    }
}
