public abstract class Lame extends Objet {
    public Lame(String nom) {
        super(nom);
    }

    @Override
    public void examiner() {
        this.decrire();
    }
}
