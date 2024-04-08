package Model;

import java.util.HashSet;

public class Porte extends EntiteVivante {
    private HashSet<Salle> salles;
    private Clef clef;

    public Porte(Boolean verrouille, Clef clef, String nom) {
        super(nom, verrouille);
        this.salles = new HashSet<>();
        this.clef = clef;
    }

    public HashSet<Salle> getSalles() {
        return salles;
    }

    public void setSalles(Salle s1, Salle s2) {
        this.salles.add(s1);
        this.salles.add(s2);
    }

    public Clef getClef() {
        return clef;
    }
}
