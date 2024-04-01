public class Ordinateur extends EntiteVivante {
    private IA ia;

    public Ordinateur(int id, String nom, Boolean verrouille, IA ia) {
        super(id, nom, verrouille);
        this.ia = ia;
    }

    @Override
    public void interagir(Joueur moi) {
        // TODO Auto-generated method stub
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
