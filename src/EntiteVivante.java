public abstract class EntiteVivante extends Descriptible implements Interagissable{
    protected Boolean verrouille;

    public EntiteVivante(String nom, Boolean verrouille) {
        super(nom);
        this.verrouille = verrouille;
    }

    @Override
    public void examiner() {
        this.decrire();
    }
}
