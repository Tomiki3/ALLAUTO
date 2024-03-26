import java.util.HashSet;

public class Porte extends EntiteVivante {
    private HashSet<Salle> salles;
    private Clef clef;

    public Porte(int id, String nom, Boolean verrouille) {
        super(id, nom, verrouille);
        this.salles = new HashSet<>();
    }

    public void setSalles(Salle s1, Salle s2) {
        this.salles.add(s1);
        this.salles.add(s2);
    }

    public Boolean ouvrir(Clef c) {
        return c.getId() == this.clef.getId();
    }

    @Override
    public void interagir() {
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

    @Override
    public void quitter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quitter'");
    }

}
