package Model;

public abstract class Localisation extends Descriptible{

    public Localisation(String nom) {
        super(nom);
    }

    /**
     * Regarde si le lieu courant possède l'objet qu'on lui recherche
     * @param nomDesc
     * @return le descriptible recherché si il est contenu dans la loc. Null sinon.
     */
    public abstract Descriptible contains(String nomDesc);
}
