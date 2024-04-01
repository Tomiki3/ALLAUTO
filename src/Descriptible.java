public abstract class Descriptible {
    private int id;
    private String nom;
    private String description;
    // Description recursive
    public abstract void examiner();

    public Descriptible(int id, String nom, String descr) {
        this.id = id;
        this.nom = nom;
        this.description = descr;
    }

    public Descriptible(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.description = "";
    }

    public void setDescription(String texte) {
        this.description = texte;
    }

    public int getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    // Description non récursive 
    public void decrire() {
        System.out.println(description);
    }
}
