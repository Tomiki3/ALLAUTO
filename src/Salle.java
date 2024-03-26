import java.util.HashSet;
import java.util.Iterator;

public class Salle extends Descriptible {
    private HashSet<Meuble> meubles;

    public Salle(int id, String nom) {
        super(id, nom);
        this.meubles = new HashSet<>();
    }

    public HashSet<Meuble> getMeubles() {
        return this.meubles;
    }

    public void addMeuble(Meuble m) {
        meubles.add(m);
    }

    /**
     * Verifie si il y a un Meuble nommé "nomMeuble" dans la liste des meubles
     * Si oui, return le Meuble. Sinon, return null.
     * @param nomMeuble Le nom du meuble que l'on recherche
     * @return
     */
    public Meuble containsMeuble(String nomMeuble) {
        Iterator<Meuble> it = meubles.iterator();

        while(it.hasNext()) {
            Meuble curr = it.next();

            if(curr.getNom().equals(nomMeuble)) {
                return curr;
            }
        }

        return null;
    }

    @Override
    public void examiner() {
        this.decrire();
        
        System.out.print("La salle contient : ");
        
        Iterator<Meuble> it = meubles.iterator();
        while (it.hasNext()) {
            System.out.print(it.next().getNom());

            if (it.hasNext())
                System.out.println(", ");
        }
        System.out.println(".");
    }

    @Override
    public void quitter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quitter'");
    }
}