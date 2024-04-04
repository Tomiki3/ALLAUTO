import java.util.HashSet;
import java.util.Iterator;

public abstract class Meuble extends Descriptible {
    private HashSet<Objet> objets;
    private HashSet<EntiteVivante> interagissables;

    public Meuble(String nom) {
        super(nom);
        this.objets = new HashSet<>();
        this.interagissables = new HashSet<>();
    }

    public void addObjet(Objet o) {
        objets.add(o);
    }

    public void addEntitViv(EntiteVivante e) {
        this.interagissables.add(e);
    }

    public boolean removeObjet(Objet o) {
        return this.objets.remove(o);
    }

    /**
     * Regarde si l'objet désigné par la chaine de caractère est contenue dans le meuble.
     * Si oui, retourne l'objet.
     * @return
     */
    public Objet containsObjet(String nomObjet) {
        Iterator<Objet> it = objets.iterator();

        while(it.hasNext()) {
            Objet curr = it.next();

            if(curr.getNom().equals(nomObjet)) {
                return curr;
            }
        }

        return null;
    }

    /**
     * Regarde si l'entité vivante désigné par la chaine de caractère est contenue dans le meuble.
     * Si oui, retourne l'entité vivante.
     * @return
     */
    public EntiteVivante containsViv(String nomViv) {
        Iterator<EntiteVivante> it = interagissables.iterator();

        while(it.hasNext()) {
            EntiteVivante curr = it.next();

            if(curr.getNom().equals(nomViv)) {
                return curr;
            }
        }

        return null;
    }


    @Override
    public void examiner() {
        this.decrire();

        // Print l'ensemble des objets posés sur le meuble
        System.out.print("Sur la " + getNom() + " se trouvent : ");
        Iterator<Objet> it = objets.iterator();
        while (it.hasNext()) {
            System.out.print(it.next().getNom());

            if (it.hasNext())
                System.out.print(", ");
        }
        System.out.println(".");

        // Print l'ensemble des interagissables posés sur le meuble
        System.out.print("On y voit aussi : ");
        Iterator<EntiteVivante> newIt = interagissables.iterator();
        while (newIt.hasNext()) {
            System.out.print(newIt.next().getNom());

            if (newIt.hasNext())
                System.out.print(", ");
        }
        System.out.println(".");
    }
}
