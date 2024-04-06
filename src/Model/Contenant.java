package Model;

import java.util.HashSet;
import java.util.Iterator;

public abstract class Contenant extends EntiteVivante {
    private HashSet<Objet> objets;

    public Contenant(String nom, Boolean verrouille) {
        super(nom, verrouille);
        this.objets = new HashSet<>();
    }

    public HashSet<Objet> getObjets() {
        return objets;
    }

    public void addObjet(Objet o) {
        objets.add(o);
    }

    public void removeObjet(Objet o) {
        objets.remove(o);
    }

    // Même méthode que dans Meuble, ça fait chier
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
}