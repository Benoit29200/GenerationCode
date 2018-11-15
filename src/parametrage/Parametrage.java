package parametrage;

import java.util.ArrayList;

public class Parametrage {
    private static Parametrage ourInstance = new Parametrage();
    ArrayList<PrimitiveParametrage> primitives;

    public static Parametrage getInstance() {
        return ourInstance;
    }

    private Parametrage(){
        primitives = new ArrayList<>();
    }

    public void parametrage(String filename) {
        this.primitives = ParserParametrage.getInstance().parse(filename);
    }

    public ArrayList<PrimitiveParametrage> getPrimitives() {
        return primitives;
    }
}
