package Model;

import java.util.HashSet;
import java.util.Iterator;

public abstract class Meuble extends Localisation {
    private HashSet<Objet> objets;
    private HashSet<EntiteVivante> interagissables;

    public Meuble(String nom) {
        super(nom);
        this.objets = new HashSet<>();
        this.interagissables = new HashSet<>();
    }

    public HashSet<Objet> getObjets() {
        return objets;
    }

    public HashSet<EntiteVivante> getInteragissables() {
        return interagissables;
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
    public Descriptible contains(String nomDesc) {
        Objet o = this.containsObjet(nomDesc);
        EntiteVivante ev = this.containsViv(nomDesc);

        if (o != null) {return o;}
        if (ev != null) {return ev;}

        return null;
    }

}
