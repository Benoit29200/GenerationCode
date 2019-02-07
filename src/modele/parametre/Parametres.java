package modele.parametre;

import java.util.ArrayList;

public class Parametres {

    ArrayList<PrimitiveParam> primitives;
    ArrayList<TypeParam> types;

    public Parametres() {
        primitives = new ArrayList<>();
        types = new ArrayList<>();
    }

    public ArrayList<PrimitiveParam> getPrimitives() {
        return primitives;
    }

    public void setPrimitives(ArrayList<PrimitiveParam> primitives) {
        this.primitives = primitives;
    }

    public ArrayList<TypeParam> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<TypeParam> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "Parametres{" +
                "primitives=" + primitives +
                ", types=" + types +
                '}';
    }
}
