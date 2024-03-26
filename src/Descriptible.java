public abstract class Descriptible {
    private int id;
    private String nom;
    public abstract void examiner();
    public abstract void quitter();

    public Descriptible(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getItd() {
        return this.id;
    }
}
