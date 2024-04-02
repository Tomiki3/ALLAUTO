import java.util.HashSet;

public abstract class Objet extends Descriptible {
    public Objet(int id, String nom) {
        super(id, nom);
    }
    
    // Pour un objet, l'examiner revient à le décrire
    public void examiner() {
        this.decrire();
    }

    public void prendre(Joueur moi) {
        moi.getInventaire().addObjet(this);
        moi.getMeuble().removeObjet(this);
        System.out.println(this.getNom() + " a été ajouté à votre inventaire.");
    }
}
