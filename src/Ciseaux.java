public class Ciseaux extends Lame {
    public Ciseaux(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void examiner() {
        throw new UnsupportedOperationException("Unimplemented method 'examiner'");
    }

    @Override
    public void quitter() {
        throw new UnsupportedOperationException("Unimplemented method 'quitter'");
    }
}
