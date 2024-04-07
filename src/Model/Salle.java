package Model;

import java.util.HashSet;
import java.util.Iterator;

public class Salle extends Localisation {
    private HashSet<Meuble> meubles;

    public Salle() {
        super("Salle");
        this.meubles = new HashSet<>();
    }

    public Salle(String nom) {
        super(nom);
        this.meubles = new HashSet<>();
    }

    public HashSet<Meuble> getMeubles() {
        return this.meubles;
    }

    public void addMeuble(Meuble m) {
        meubles.add(m);
    }

    /**
     * Verifie si il y a un Meuble nommé "nomMeuble" dans la liste des meubles
     * Si oui, return le Meuble. Sinon, return null.
     * @param nomMeuble Le nom du meuble que l'on recherche
     * @return
     */
    @Override
    public Descriptible contains(String nomMeuble) {
        Iterator<Meuble> it = meubles.iterator();

        while(it.hasNext()) {
            Meuble curr = it.next();

            if(curr.getNom().equals(nomMeuble)) {
                return curr;
            }
        }
        return null;
    }
}