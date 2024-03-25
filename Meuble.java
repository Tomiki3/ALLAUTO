import java.util.HashSet;

public abstract class Meuble extends Descriptible {
    private HashSet<Objet> objets;

    public Meuble(int id, String nom) {
        super(id, nom);
        this.objets = new HashSet<>();
    }

    public void addObjet(Objet o) {
        objets.add(o);
    }

}
