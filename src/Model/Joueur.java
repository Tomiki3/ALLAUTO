package Model;

public class Joueur {
    private Salle localisation;
    private Meuble meuble;  //peut valoir null si le joueur est juste dans une salle
    private Inventaire inventaire;
    private boolean enVie;

    public Joueur(Salle salle1) {
        this.localisation = salle1;
        this.meuble = null;
        this.inventaire = new Inventaire();
        this.enVie = true;
    }
    
    public void setLocalisation (Salle newSalle) {
        this.localisation = newSalle;
    }

    /**
     * Surcharge de la méthode. La localisation peut autant convenir à une salle qu'à un meuble.
     * @param newMeuble
     */
    public void setLocalisation (Meuble newMeuble) {
        this.meuble = newMeuble;
    }

    public Salle getLocalisation() {
        return this.localisation;
    }

    public void setMeuble(Meuble meuble) {
        this.meuble = meuble;
    }

    public Meuble getMeuble() {
        return this.meuble;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public void setEnVie(boolean enVie) {
        this.enVie = enVie;
    }

    public boolean getVie() {
        return this.enVie;
    }
}
