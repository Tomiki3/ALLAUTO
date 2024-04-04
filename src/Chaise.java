
public class Chaise extends EntiteVivante {
    public Chaise(String nom, Boolean verrouille) {
        super(nom, false);
    }

    @Override
    public void interagir(Joueur moi) {
        throw new UnsupportedOperationException("Unimplemented method 'interagir'");
    }

    @Override
    public void arreter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'arreter'");
    }

    @Override
    public void examiner() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'examiner'");
    }
    
}
