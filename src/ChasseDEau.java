public class ChasseDEau  extends EntiteVivante {
    public ChasseDEau(int id, String nom, Boolean verrouille) {
        super(id, nom, false);
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
