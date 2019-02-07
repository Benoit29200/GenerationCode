package parser.parserMetamodele;

import modele.metamodele.Attribut;
import modele.metamodele.Entite;
import modele.metamodele.Modele;
import org.w3c.dom.Node;
import parametrage.Parametres;
import parametrage.ParserParam;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParserMetamodele {

    private static ParserMetamodele instance;
    private Modele myModele;

    private ParserMetamodele() {
        myModele = new Modele();
    }

    public static ParserMetamodele getInstance() {
        if (instance == null) instance = new ParserMetamodele();
        return instance;
    }


    public Modele parse(String filename, String parametrageFilename) {

        try {
            final org.w3c.dom.Element racine = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename)).getDocumentElement();
            parsePackage(racine, parametrageFilename);
            return this.myModele;

        } catch (Exception e) {
            System.err.println("Erreur lors du parsage du document xml");
        }
        return null;
    }


    private void parsePackage(org.w3c.dom.Element node, String parametrageFilename) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()) {
                        case "Modele": {
                            String nomPackage = elem.getAttribute("nom");
                            this.myModele = new Modele(nomPackage);
                            parseEntite(elem, this.myModele, parametrageFilename);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse modele :" + e.getMessage());
        }
    }

    private void parseEntite(org.w3c.dom.Element node, Modele parent, String parametrageFilename) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()) {
                        case "Entite": {
                            String subtypeof = elem.getAttribute("subtypeof");
                            String nomEntite = elem.getAttribute("nom");
                            Entite monEntite = new Entite(nomEntite, subtypeof);
                            parent.getEntites().add(monEntite);
                            parseAttribut(elem, monEntite, parametrageFilename);

                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse entite : " + e.getMessage());
        }
    }

    private void parseAttribut(org.w3c.dom.Element node, Entite parent, String parametrageFilename) {
        try {

            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()) {
                        case "Attribut": {
                            String nomAttribut = elem.getAttribute("nom");
                            String typeAttribut = elem.getAttribute("type");
                            Attribut p = new Attribut(nomAttribut,typeAttribut);
                            parent.addAttribut(p);

                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erreur parse attribut : " + e.getMessage());
        }
    }
}
