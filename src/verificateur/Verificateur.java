package verificateur;

import modele.metamodele.Attribut;
import modele.metamodele.Entite;
import modele.metamodele.Modele;
import parametrage.PrimitiveParam;

import java.util.ArrayList;

public class Verificateur {
    private static Verificateur ourInstance = new Verificateur();

    public static Verificateur getInstance() {
        return ourInstance;
    }

    private Verificateur() {
    }

    public boolean CirculariteOK(Modele p, Entite e ){

        if(e.getSubtypeof().isEmpty()) return true;

        boolean containsEntityMother = false;
        boolean attributInMotherAndDaughter = false;
        Entite mother=null;

        for(Entite e1: p.getEntites()){
            if(e1.getNom().equals(e.getSubtypeof())){
                containsEntityMother = true;
                mother = e1;
            }
        }

        if(!containsEntityMother){
            System.err.println("La classe mère de la classe \""+e.getNom()+"\" n'existe pas: \'"+e.getSubtypeof()+"\"\n");
            return false;
        }

        if(mother.getSubtypeof().equals(e.getNom())){
            System.err.println("Circularité parenté entre les classes \""+mother.getNom()+"\" et \""+e.getNom()+"\"\n");
            return false;
        }

        for(Attribut aMother: mother.getAttributs()){
            for(Attribut aDaughter : e.getAttributs()){
                if(aMother.getNom().equals(aDaughter.getNom())){
                    System.err.println("L'attribut \""+aMother.getNom()+"\" est présent dans la classe mère \""+mother.getNom()+"\" et sa classe fille \""+e.getNom()+"\"\n");
                    attributInMotherAndDaughter = true;
                }
            }
        }

        if(attributInMotherAndDaughter){
            return false;
        }


        return true;
    }
    public boolean typeAttributOK(Modele modele,ArrayList<Attribut> attributs, ArrayList<PrimitiveParam> primitives){

        boolean attributsOK = true;

        for(Attribut a: attributs){

            boolean typeAttributOK = false;

            for(PrimitiveParam primitive : primitives){
                if(a.getType().equals(primitive.getNom())){
                    typeAttributOK = true;
                }
            }

            if(!typeAttributOK){
                for(Entite e : modele.getEntites()){
                    if(e.getNom().equals(a.getType())){
                        typeAttributOK = true;
                    }
                }
            }

            if (!typeAttributOK) {
                System.err.println("L'attribut \""+a.getNom()+"\" est de type inconnu: \""+a.getType()+"\"\n");
                attributsOK = false;
            }
        }
        return attributsOK;
    }
}
