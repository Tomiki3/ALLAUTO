public abstract class Descriptible {
    private String nom;
    private String description;
    // Description recursive
    public abstract void examiner();

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

    // Description non récursive 
    public void decrire() {
        System.out.println(description);
    }
}
