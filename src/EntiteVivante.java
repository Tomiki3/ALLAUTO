public abstract class EntiteVivante extends Descriptible implements Interagissable{
    private Boolean verrouille;

    public EntiteVivante(int id, String nom, Boolean verrouille) {
        super(id, nom);
        this.verrouille = verrouille;
    }
}
