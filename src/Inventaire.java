import java.util.HashSet;

public class Inventaire extends Descriptible {
    private Objet objetEquipe;
    private HashSet<Objet> objets;

    public Inventaire(int id) {
        super(id, "Inventaire");
        this.objets = new HashSet<>();
    }

    public void addObjet(Objet o) {
        objets.add(o);
    }

    public void setObjetEquipe(Objet o) {
        assert(objets.contains(o));
        if (objetEquipe != null) {
            objets.add(objetEquipe);
        }
        objetEquipe = o;
        objets.remove(o);
    }

    @Override
    public void examiner() {
        throw new UnsupportedOperationException("Unimplemented method 'examiner'");
    }
}
