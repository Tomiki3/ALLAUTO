package Model;

public abstract class EntiteVivante extends Descriptible {
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
}
