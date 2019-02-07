package parametrage;

public class PrimitiveSimpleParam extends PrimitiveParam {

    public PrimitiveSimpleParam() {

    }

    public PrimitiveSimpleParam(String nom, String type) {
        this.nom = nom;
        this.type = type;
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
}
