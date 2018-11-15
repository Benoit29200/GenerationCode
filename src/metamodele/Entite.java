package metamodele;

import com.sun.xml.internal.ws.util.StringUtils;
import metamodele.attribut.AssociationSimple;
import metamodele.attribut.Primitive;
import metamodele.attribut.UnresolvedAttribut;
import verification.Verificateur;

import java.util.ArrayList;

public class Entite {

    String nom;

    ArrayList<Attribut> attributs;

    Modele parent;

    String subtypeof = "";

    public Entite(String nom, Modele parent, String subtypeof) {
        this.nom = StringUtils.capitalize(nom);
        this.attributs = new ArrayList<>();
        this.parent = parent;
        this.subtypeof = subtypeof;
    }

    public ArrayList<Attribut> getAttributs() {
        return attributs;
    }

    public String getNom() {
        return nom;
    }

    public Modele getParent() {
        return parent;
    }

    public String getSubtypeof() {
        return subtypeof;
    }

    public String generateCodeEntiteToJava() {
        this.traitementUnresolvedAttribut();

        return this.packageEntite()
                + this.entete()
                + this.definitionAttribut()
                + this.constructor()
                + this.getterSetterAttribut()
                + "}";
    }


    private String packageEntite() {
        return "package " + this.parent.getNom() + ";";
    }

    private String entete() {
        if (subtypeof != "") {
            if (!Verificateur.getInstance().SubtypeIsValid(this)) {
                System.exit(0);
                return null;
            } else {
                return "public class " + this.nom + " extends " + this.subtypeof + " {";
            }
        } else {
            return "public class " + this.nom + " {";
        }
    }

    private String definitionAttribut() {
        String definitions="";
        for (Attribut element : attributs) {
            definitions += element.acceptEntiteForDefinition(this);
        }
        return definitions;
    }

    private void traitementUnresolvedAttribut() {
        for (Attribut element : attributs) {
            element.acceptEntiteForUnresolvedAttribut(this);
        }
    }

    private String constructor() {
        return "public " + this.nom + "() { }";
    }

    private String getterSetterAttribut() {
        String getterSetter = "";
        for (Attribut element : attributs) {
            getterSetter += element.acceptEntiteForGetterSetter(this);
        }
        return getterSetter;
    }


    public String definitionPrimitiveToJava(Primitive primitive) {
        return primitive.DefinitionToJava();
    }

    public String getterSetterAssociationSimpleToJava(AssociationSimple assoSimple) {
        return assoSimple.getterSetterToJava();
    }

    public String definitionAssociationSimpleToJava(AssociationSimple assoSimple) {
        return assoSimple.DefinitionToJava();
    }

    public String getterSetterPrimitiveToJava(Primitive primitive) {
        return primitive.getterSetterToJava();
    }

    public void tryToResolveUnresolvedAttribut(UnresolvedAttribut nonConnu) {
        for (Entite entite : this.parent.getEntites()) {
            if (nonConnu.getType().equals(entite.getNom())) {
                //TODO  on doit enlever le UnresolvedSymbol et créer une association simple
                AssociationSimple assoSimple = new AssociationSimple(nonConnu.getNom(), entite);
                this.getAttributs().add(assoSimple);
                this.getAttributs().remove(nonConnu);

            }
        }
    }
}
