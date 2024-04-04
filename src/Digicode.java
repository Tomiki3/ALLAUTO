public class Digicode extends EntiteVivante {
    private Porte porte;
    private String secretCode;

    public Digicode(String nom, Boolean verrouille) {
        super(nom, false);
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
