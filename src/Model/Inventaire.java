package Model;

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

    public HashSet<Objet> getObjets() {
        return objets;
    }

    public void addObjet(Objet o) {
        objets.add(o);
    }

    // ajout d'un objet dans l'inventaire
    public void setObjetEquipe(Objet o) {
        assert(objets.contains(o));
        if (objetEquipe != null) {
            objets.add(objetEquipe);
        }
        objetEquipe = o;
        objets.remove(o);
    }

    // check si un objet est présent dans l'inventaire
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
}
