package verificateur;

import modele.metamodele.Attribut;
import modele.metamodele.Entite;
import modele.metamodele.Modele;
import modele.parametre.Parametres;
import modele.parametre.PrimitiveParam;
import modele.parametre.TypeParam;

import java.util.ArrayList;

public class Verificateur {
    private static Verificateur ourInstance = new Verificateur();

    public static Verificateur getInstance() {
        return ourInstance;
    }

    private Verificateur() {
    }

    public boolean CirculariteOK(Modele p, Entite e ){

        // si on a pas de sous type, pas besoin de vérifier la circularité
        if(e.getSubtypeof().isEmpty()) return true;

        boolean containsEntityMother = false;
        boolean attributInMotherAndDaughter = false;
        Entite mother=null;

        // On vérifie que la classe mère existe
        for(Entite e1: p.getEntites()){
            if(e1.getNom().equals(e.getSubtypeof())){
                containsEntityMother = true;
                mother = e1;
            }
        }

        // on affiche un message d'erreur si la classe mère n'existe pas.
        if(!containsEntityMother){
            System.err.println("La classe mère de la classe \""+e.getNom()+"\" n'existe pas: \'"+e.getSubtypeof()+"\"\n");
            return false;
        }

        // si la classé mère hérite de la classe fille, il y a un problème donc erreur
        if(mother.getSubtypeof().equals(e.getNom())){
            System.err.println("Circularité parenté entre les classes \""+mother.getNom()+"\" et \""+e.getNom()+"\"\n");
            return false;
        }

        // on vérifie qu'un attribut de la classe fille n'est pas également présent dans la classe mère
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
    public boolean typeAttributOK(ArrayList<Modele> modeles, ArrayList<Attribut> attributs, Parametres lesParametres){

        boolean attributsOK = true;

        for(Attribut a: attributs){

            boolean typeAttributOK = false;

            // si le type de l'attribut est une primitive, c'est ok
            for(PrimitiveParam primitive : lesParametres.getPrimitives()){
                if(a.getType().equals(primitive.getNom())){
                    typeAttributOK = true;
                }
            }

            //TODO a modifier pour incorporer tout les packages
            if(!typeAttributOK){
                for(Modele modele: modeles){
                    for(Entite e : modele.getEntites()){
                        if(e.getNom().equals(a.getType())){
                            typeAttributOK = true;
                        }
                    }
                }

            }

            // si l'attribu est une collection c'est ok
            if(!typeAttributOK){
                for(TypeParam type: lesParametres.getTypes()){
                    if(a.getType().equals(type.getNom())){
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
