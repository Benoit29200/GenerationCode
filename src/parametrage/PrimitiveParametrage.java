package parametrage;

public class PrimitiveParametrage {

    String name;
    String type;

    public PrimitiveParametrage(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        return "nom: "+this.name+" ; type: "+this.type+"\n";
    }
}
