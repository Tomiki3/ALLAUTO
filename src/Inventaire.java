import java.util.HashSet;
import java.util.Iterator;

public class Inventaire extends Descriptible {
    private Objet objetEquipe;
    private HashSet<Objet> objets;

    public Inventaire() {
        super("Inventaire");
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

    public Objet contains(String nomObjet) {
        Iterator<Objet> it = objets.iterator();

        while(it.hasNext()) {
            Objet curr = it.next();

            if(curr.getNom().equals(nomObjet)) {
                return curr;
            }
        }

        return null;
    }

    @Override
    public void examiner() {
        this.decrire();

        Iterator<Objet> it = objets.iterator();
        System.out.print("\nVoici ce qu'il contient : ");
        while (it.hasNext())
        {
            Objet curr = it.next();
            System.out.print(curr.getNom());
            if (it.hasNext()) {System.out.print(", ");}
        }
        System.out.println(".");

        if (objetEquipe != null)
            System.out.println("\nVous tenez " + objetEquipe.getNom() + " en main.");
    }
}
