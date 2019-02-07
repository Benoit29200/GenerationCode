package parser.parserParametre;

import modele.parametre.Parametres;
import modele.parametre.PrimitiveParam;
import modele.parametre.TypeParam;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParserParam {
    private static ParserParam ourInstance = new ParserParam();
    Parametres lesParametres;

    public static ParserParam getInstance() {
        return ourInstance;
    }

    private ParserParam() {
        lesParametres = new Parametres();
    }

    public Parametres parse(String filename){
        try {
            final org.w3c.dom.Element racine = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename)).getDocumentElement();
            parsePrimitive(racine);
            return this.lesParametres;

        } catch (Exception e) {
            System.err.println("Erreur lors du parsage du document "+filename+"\n");
            return null;
        }
    }

    private void parsePrimitive(org.w3c.dom.Element node){


        try {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()){
                        case "primitive":{
                            String name = elem.getAttribute("name");
                            String type = elem.getAttribute("type");
                            PrimitiveParam primitive = new PrimitiveParam(name,type);
                            this.lesParametres.getPrimitives().add(primitive);
                            break;
                        }
                        case "type":{
                            String name = elem.getAttribute("name");
                            String type = elem.getAttribute("type");
                            String monPackage = elem.getAttribute("package");
                            TypeParam typeParam = new TypeParam(name,type,monPackage);
                            this.lesParametres.getTypes().add(typeParam);
                            break;
                        }
                    }
                }
            }
        }catch (Exception e) {
            System.err.println("erreur parse modele :"+e.getMessage());
        }
    }
}
