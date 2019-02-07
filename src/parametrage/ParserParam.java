package parametrage;

import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class ParserParam {
    private static ParserParam ourInstance = new ParserParam();

    public static ParserParam getInstance() {
        return ourInstance;
    }

    private ParserParam() {
    }

    public ArrayList<PrimitiveParam> parse(String filename){
        try {
            final org.w3c.dom.Element racine = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename)).getDocumentElement();
            return parsePrimitive(racine);

        } catch (Exception e) {
            System.err.println("Erreur lors du parsage du document "+filename+"\n");
            return null;
        }
    }

    private ArrayList<PrimitiveParam> parsePrimitive(org.w3c.dom.Element node){

        ArrayList<PrimitiveParam> primitives = new ArrayList<>();

        try {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final org.w3c.dom.Element elem = (org.w3c.dom.Element) node.getChildNodes().item(i);

                    switch (elem.getNodeName()){
                        case "primitive":{
                            String name = elem.getAttribute("name");
                            String type = elem.getAttribute("type");
                            String packageName = elem.getAttribute("package");
                            if(packageName.equals("")){
                                PrimitiveSimpleParam primitive = new PrimitiveSimpleParam(name,type);
                                primitives.add(primitive);
                            }else{
                                PrimitiveMultipleParam assoMultiple = new PrimitiveMultipleParam(name,type,packageName);
                                primitives.add(assoMultiple);
                            }

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
