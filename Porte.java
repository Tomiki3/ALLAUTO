import java.util.HashSet;

public class Porte extends EntiteVivante {
    private HashSet<Salle> salles;

    public Porte(int id, String nom, Boolean verrouille) {
        super(id, nom, verrouille);
        this.salles = new HashSet<>();
    }

    public void setSalles(Salle s1, Salle s2) {
        this.salles.add(s1);
        this.salles.add(s2);
    }

}
