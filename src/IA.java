public class IA extends EntiteVivante{

    public IA(String nom, Boolean verrouille) {
        super(nom, verrouille);
    }

    @Override
    public void interagir(Joueur moi) {
        // Faire un arbre des choix avec les répliques possibles de l'IA
    }

    @Override
    public void arreter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'arreter'");
    }
    
}
