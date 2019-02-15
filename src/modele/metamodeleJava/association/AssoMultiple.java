package modele.metamodeleJava.association;

import modele.metamodeleJava.Association;

public abstract class AssoMultiple extends Association {

    protected int cardMin;
    protected int cardMax;


    public int getCardMin() {
        return cardMin;
    }

    public void setCardMin(int cardMin) {
        this.cardMin = cardMin;
    }

    public int getCardMax() {
        return cardMax;
    }

    public void setCardMax(int cardMax) {
        this.cardMax = cardMax;
    }

}
