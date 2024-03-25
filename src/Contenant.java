import java.util.HashSet;

public abstract class Contenant extends EntiteVivante {
    private HashSet<Objet> objets;

    public Contenant(int id, String nom, Boolean verrouille) {
        super(id, nom, verrouille);
        this.objets = new HashSet<>();
    }

    public void addObjet(Objet o) {
        objets.add(o);
    }
}