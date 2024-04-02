import java.util.HashSet;
import java.util.Iterator;

public class Inventaire extends Descriptible {
    private Objet objetEquipe;
    private HashSet<Objet> objets;

    public Inventaire(int id) {
        super(id, "Inventaire");
        this.objets = new HashSet<>();
        this.setDescription("Ceci est votre inventaire.\nVous pouvez y stocker autant d'objets que vous le voulez.");
    }

    public Objet getObjetEquipe() {
        return this.objetEquipe;
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
        this.decrire();

        Iterator<Objet> it = objets.iterator();
        System.out.print("Voici ce qu'il contient : ");
        while (it.hasNext())
        {
            System.out.print(it.next().getNom());
            if (it.hasNext()) {System.out.print(", ");}
        }
        System.out.println(".");
    }
}
