package generateur;

import metamodele.Entite;
import metamodele.Modele;
import parser.Parser;

import java.io.File;
import java.io.FileOutputStream;


public class Generateur {
    private static Generateur ourInstance = new Generateur();

    public static Generateur getInstance() {
        return ourInstance;
    }

    private Generateur() {
    }

    public void generate(String filename, String generateFolder){
        Modele modele = Parser.getInstance().parse(filename);

        for(Entite entite:modele.getEntites()){
            try{
                File repertory = new File(modele.getNom());
                repertory.mkdirs();
                FileOutputStream fos = new FileOutputStream(new File(modele.getNom()+"/"+entite.getNom()+".java"));
                fos.write(entite.generateCodeEntiteToJava().getBytes());
                fos.close();
            }catch(Exception e){
                System.err.println("Erreur lors de l'écriture du code de l'entité '"+entite.getNom()+"'.\n"+e.getMessage());
            }
        }
    }


}
