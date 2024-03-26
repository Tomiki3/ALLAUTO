public class Joueur {
    private Salle localisation;
    private Meuble meuble;  //peut valoir null si le joueur est juste dans une salle
    private Inventaire inventaire;

    public Joueur(Salle salle1) {
        this.localisation = salle1;
        this.meuble = null;
        this.inventaire = new Inventaire(0);
    }
    
    public void setLocalisation (Salle newSalle) {
        this.localisation = newSalle;
    }

    public Salle getLocalisation() {
        return this.localisation;
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
