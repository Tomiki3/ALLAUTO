public class Ordinateur extends EntiteVivante {
    private IA ia;

    public Ordinateur(int id, String nom, Boolean verrouille, IA ia) {
        super(id, nom, verrouille);
        this.ia = ia;
    }    
}
