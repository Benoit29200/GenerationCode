package parametrage;

public class PrimitiveMultipleParam extends PrimitiveParam {

    private String myPackage;

    public PrimitiveMultipleParam() {
    }

    public PrimitiveMultipleParam(String nom,String type,String myPackage){
        this.nom = nom;
        this.type = type;
        this.myPackage = myPackage;
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
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getMyPackage() {
        return myPackage;
    }

    public void setMyPackage(String myPackage) {
        this.myPackage = myPackage;
    }
}
