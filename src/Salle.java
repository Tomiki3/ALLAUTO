import java.util.HashSet;
import java.util.Iterator;

public class Salle extends Descriptible {
    private HashSet<Meuble> meubles;

    public Salle(int id, String nom) {
        super(id, nom);
        this.meubles = new HashSet<>();
    }

    public void addMeuble(Meuble m) {
        meubles.add(m);
    }

    @Override
    public void examiner() {
        this.decrire();
        Iterator<Meuble> it = meubles.iterator();
        while (it.hasNext()) {
            System.out.println(", " + it.next().getNom());
        }
        System.out.println(".");
    }

    @Override
    public void quitter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quitter'");
    }
}