import java.util.HashSet;

public class Porte extends EntiteVivante {
    private HashSet<Salle> salles;
    private Clef clef;

    public Porte(int id, Boolean verrouille, Clef clef) {
        super(id, "porte", verrouille);
        this.salles = new HashSet<>();
        this.clef = clef;
    }

    public void setSalles(Salle s1, Salle s2) {
        this.salles.add(s1);
        this.salles.add(s2);
    }

    public Boolean ouvrir(Clef c) {
        return c.getId() == this.clef.getId();
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
