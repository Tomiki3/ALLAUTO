package Model;


/**
 * Classe des objets pouvant être décrit.
 */
public abstract class Descriptible {
    private String nom;
    private String description;

    public Descriptible(String nom, String descr) {
        this.nom = nom;
        this.description = descr;
    }

    public Descriptible(String nom) {
        this.nom = nom;
        this.description = "";
    }

    public void setDescription(String texte) {
        this.description = texte;
    }

    public String getNom() {
        return this.nom;
    }

    public String getDescription() {
        return description;
    }
}
