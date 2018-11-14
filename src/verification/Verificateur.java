package verification;

import metamodele.Attribut;
import metamodele.Entite;

public class Verificateur {
    private static Verificateur ourInstance = new Verificateur();

    public static Verificateur getInstance() {
        return ourInstance;
    }

    private Verificateur() {
    }

    public boolean SubtypeIsValid(Entite source){


        boolean isValid = true;

        if(!subtypeofIsPresent(source)){
            isValid = false;
            System.err.println("La classe '"+source.getSubtypeof()+"' n'existe pas.\n");
        }else{
            Entite mere = getMereBySource(source);
            if(mere.getSubtypeof().equals(source.getNom())){
                isValid = false;
                System.err.println("Circularité interdite : La classe '"+source.getNom()+"' hérite de '"+mere.getSubtypeof()+"' et inversement.\n");
            }else{
                for(Attribut attributSource:source.getAttributs()){
                    for(Attribut attributMere:mere.getAttributs()){
                        if(attributSource.getNom().equals(attributMere.getNom())){
                            isValid = false;
                            System.err.println("L'attribut '"+attributMere.getNom()+"'existe dans la classe '"+source.getNom()+"' et dans sa classe mère '"+mere.getNom()+"'.\n");
                        }
                    }
                }
            }
        }
        return isValid;
    }

    private boolean subtypeofIsPresent(Entite source){
        for(Entite entite:source.getParent().getEntites()){
            if(entite.getNom().equals(source.getSubtypeof())){
                 return true;
            }
        }
        return false;
    }


    private Entite getMereBySource(Entite source){
        for(Entite entite:source.getParent().getEntites()){
            if(entite.getNom().equals(source.getSubtypeof())){
                return entite;
            }
        }
        return null;
    }

}
