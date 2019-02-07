package modele.metamodeleJava;

import java.util.ArrayList;

public class Package {

    private String nom;
    ArrayList<Class> classes;

    public Package(String nom, ArrayList<Class> classes) {
        this.nom = nom;
        this.classes = classes;
    }

    public Package(String nom) {
        this.nom = nom;
        this.classes = new ArrayList<>();
    }

    public Package() {
        this.classes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Package{" +
                "nom='" + nom + '\'' +
                ", classes=" + classes +
                '}';
    }
}
