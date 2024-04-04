import java.util.HashSet;
import java.util.Iterator;

public class Porte extends EntiteVivante {
    private HashSet<Salle> salles;
    private Clef clef;

    public Porte(Boolean verrouille, Clef clef) {
        super("porte", verrouille);
        this.salles = new HashSet<>();
        this.clef = clef;
    }

    public void setSalles(Salle s1, Salle s2) {
        this.salles.add(s1);
        this.salles.add(s2);
    }

    @Override
    public void interagir(Joueur moi) {

        // check qu'une clef est équipée et que c'est bien la bonne clef dans le cas où la porte est fermée
        if (this.verrouille)
        {
            if ((moi.getInventaire().getObjetEquipe() instanceof Clef) &&
                ((Clef) (moi.getInventaire().getObjetEquipe())).getNom() == this.clef.getNom())
            {
                this.verrouille = false;    // ouvre la porte
                System.out.println("Vous ouvrez la porte.");
            }
            else
            {
                System.out.println("Vous n'arrivez pas à ouvrir la porte. Elle semble fermée à clef.");
                return;
            }
        }

        Iterator<Salle> it = salles.iterator();

        while (it.hasNext())    // parcourt les salles liées à la porte pour trouver celle dans laquelle on doit aller
        {
            Salle room = it.next();

            if (room.getNom() != moi.getLocalisation().getNom())
            {
                moi.setLocalisation(room);
                System.out.println("Vous passez dans la salle : " + room.getNom());
                room.examiner();
                break;
            }
        }

    }

    @Override
    public void arreter() {
        // nothing
    }
}
