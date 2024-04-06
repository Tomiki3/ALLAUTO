package Model;

public class Fenetre extends EntiteVivante {
    public Fenetre(Boolean verrouille) {
        super("fenêtre", true);
        // l'attribut "verrouillé" pour une fenêtre correspond à si elle est fermée ou non
    }
}
