public class Joueur {
    private Salle localisation;
    private Meuble meuble;  //peut valoir null si le joueur est juste dans une salle
    private Inventaire inventaire;
    private boolean enVie;

    public Joueur(Salle salle1) {
        this.localisation = salle1;
        this.meuble = null;
        this.inventaire = new Inventaire(0);
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

    public Meuble getMeuble() {
        return this.meuble;
    }

    public boolean getVie() {
        return this.enVie;
    }

    /**
     * Tue le joueur.
     */
    public void kill() {
        this.enVie = false;
    }

    /**
     * Nous fait quitter l'examination du meuble courant
     */
    public void quitter() {
        this.meuble = null;
    }  

    public void prendre(Meuble m, Objet o) {
        m.removeObjet(o);
        this.inventaire.addObjet(o);
    }

    public void equiper(Objet o) {
        this.inventaire.setObjetEquipe(o);
    }

    public void montrerInventaire() {
        System.out.println(this.inventaire);
    }
}
