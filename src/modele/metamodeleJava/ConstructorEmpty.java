package modele.metamodeleJava;

public class ConstructorEmpty extends Constructor {

    public ConstructorEmpty() {
    }

    public ConstructorEmpty(String nom){
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "ConstructorEmpty{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
