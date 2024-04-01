public class Boite extends Contenant {
    public Boite(int id, String nom, Boolean verrouille) {
        super(id, nom, verrouille);
    }

    @Override
    public void interagir(Joueur moi) {
        throw new UnsupportedOperationException("Unimplemented method 'interagir'");
    }

    @Override
    public void arreter() {
        throw new UnsupportedOperationException("Unimplemented method 'arreter'");
    }

    @Override
    public void examiner() {
        throw new UnsupportedOperationException("Unimplemented method 'examiner'");
    }  
}
