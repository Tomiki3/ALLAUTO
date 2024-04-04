public class Boite extends Contenant {
    public Boite(String nom, Boolean verrouille) {
        super(nom, verrouille);
    }

    @Override
    public void interagir(Joueur moi) {
        throw new UnsupportedOperationException("Unimplemented method 'interagir'");
    }

    @Override
    public void arreter() {
        throw new UnsupportedOperationException("Unimplemented method 'arreter'");
    }
}
