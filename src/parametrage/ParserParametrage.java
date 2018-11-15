package parametrage;

import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class ParserParametrage {
    private static ParserParametrage ourInstance = new ParserParametrage();

    public static ParserParametrage getInstance() {
        return ourInstance;
    }

    private ParserParametrage() {
    }

    public ArrayList<PrimitiveParametrage> parse(String filename){
        try {
            final org.w3c.dom.Element racine = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename)).getDocumentElement();
            return parsePrimitive(racine);

        } catch (Exception e) {
            System.err.println("Erreur lors du parsage du document "+filename+"\n");
            return null;
        }
    }

    private ArrayList<PrimitiveParametrage> parsePrimitive(org.w3c.dom.Element node){

        ArrayList<PrimitiveParametrage> primitives = new ArrayList<>();

        try {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()){
                        case "primitive":{
                            String name = elem.getAttribute("name");
                            String type = elem.getAttribute("type");
                            PrimitiveParametrage primitive = new PrimitiveParametrage(name,type);
                            primitives.add(primitive);
                            break;
                        }
                    }
                }
            }
            return primitives;
        }catch (Exception e) {
            System.out.println("erreur parse modele :"+e.getMessage());
            return null;
        }
    }
}
