public abstract class EntiteVivante extends Descriptible implements Interagissable{
    protected Boolean verrouille;

    public EntiteVivante(int id, String nom, Boolean verrouille) {
        super(id, nom);
        this.verrouille = verrouille;
    }

    @Override
    public void examiner() {
        this.decrire();

        System.out.print("Cette objet ");
        if (this.verrouille) {System.out.println("est verrouillé.");}
        else {System.out.println("n'est pas verrouillé.");}
    }
}
