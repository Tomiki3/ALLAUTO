public class Digicode extends EntiteVivante {
    private Porte porte;
    private String secretCode;

    public Digicode(int id, String nom, Boolean verrouille) {
        super(id, nom, false);
    }

    @Override
    public void interagir(Joueur moi) {
        // TODO : initialiser un scan + demander code + si code == secretCode on déverrouille la porte associée; 
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
