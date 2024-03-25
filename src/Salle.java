import java.util.HashSet;

public class Salle extends Descriptible {
    private HashSet<Meuble> meubles;
    private HashSet<EntiteVivante> entitViv;

    public Salle(int id, String nom) {
        super(id, nom);
        this.meubles = new HashSet<>();
        this.entitViv = new HashSet<>();
    }

    public void addMeuble(Meuble m) {
        meubles.add(m);
    }

    public void addEntitViv(EntiteVivante e) {
        entitViv.add(e);
    }
}