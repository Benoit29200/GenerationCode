package main;

import generateur.java.GenerateurCodeJava;
import generateur.java.GenerateurMetaJava;
import monPackage.Chaise;
import monPackage.Tabouret;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.Repository;

import static org.junit.jupiter.api.Assertions.*;

class runTest {


    @BeforeEach
    void setUp() {
        GenerateurMetaJava generateurMetaJava = GenerateurMetaJava.getInstance();
        generateurMetaJava.init("code.xml", "parametrage.xml");
        generateurMetaJava.generate("metaModeleForJava.xml");

        GenerateurCodeJava generateurCodeJava = GenerateurCodeJava.getInstance();
        if(generateurCodeJava.init("metaModeleForJava.xml", "parametrage.xml")){
            generateurCodeJava.generate("repository");
        }
    }

    @Test
    void repositoryTest(){
        Repository repository = Repository.getInstance();


        Chaise chaise = new Chaise();
        String idChaise = "chaise1";

        Tabouret tabouret = new Tabouret();
        String idTabouret = "tabouret1";

        String nomXML = "testRepository.xml";

        repository.add(idChaise, chaise);
        repository.add(idTabouret,tabouret);
        repository.serialize(nomXML);
        repository.clear();
        repository.parseRepository(nomXML);
        Chaise c = (Chaise) repository.get(idChaise);
        Tabouret t = (Tabouret) repository.get(idTabouret);

        assertTrue(c != null);
        assertTrue(t != null);
    }
}