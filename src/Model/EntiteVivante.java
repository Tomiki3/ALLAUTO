package Model;

/**
 * Décrit les objets avec lesquelles on peut intéragir
 */
public abstract class EntiteVivante extends Localisation {
    protected Boolean verrouille;

    public EntiteVivante(String nom, Boolean verrouille) {
        super(nom);
        this.verrouille = verrouille;
    }

    public Boolean getVerrouille() {
        return verrouille;
    }

    public void setVerrouille(Boolean verrouille) {
        this.verrouille = verrouille;
    }

    public Descriptible contains(String nomLoc) {
        return null;
    }
}
