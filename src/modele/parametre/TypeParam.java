package modele.parametre;

public class TypeParam {

    private String nom;
    private String type;
    private String lePackage;

    public TypeParam(String nom, String type, String lePackage) {
        this.nom = nom;
        this.type = type;
        this.lePackage = lePackage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLePackage() {
        return lePackage;
    }

    public void setLePackage(String lePackage) {
        this.lePackage = lePackage;
    }

    @Override
    public String toString() {
        return "TypeParam{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", lePackage='" + lePackage + '\'' +
                '}';
    }
}
