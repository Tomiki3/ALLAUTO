import java.util.HashSet;

public abstract class Vasque extends EntiteVivante {
    private HashSet<Objet> objets;
    
    public Vasque(String nom, Boolean verrouille) {
        super(nom, false);
        this.objets = new HashSet<>();
    }

    public void addObjet(Objet o) {
        objets.add(o);
    }
}
