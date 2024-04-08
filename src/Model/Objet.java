package Model;

/**
 * Classe des objets pouvant être ramassé et mis dans l'inventaire.
 */
public abstract class Objet extends Descriptible {
    public Objet(String nom) {
        super(nom);
    }
}
