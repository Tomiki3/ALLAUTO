package Model;

import java.util.Stack;

public class Joueur {
    private Stack<Localisation> localisation;
    private Inventaire inventaire;
    private boolean enVie;

    public Joueur(Salle salle1) {
        this.localisation = new Stack<>();
        this.localisation.push(salle1);
        this.inventaire = new Inventaire();
        this.enVie = true;
    }
    
    public void setLocalisation (Localisation newLoc) {
        if (newLoc instanceof Salle) {
            this.localisation.push(newLoc);
        }
        else if (this.localisation.peek().contains(newLoc.getNom()) != null) {
            this.localisation.push(newLoc);
        }
    }

    public Localisation getLocalisation() {
        return this.localisation.peek();
    }

    public Localisation quitterLocalisation() {
        return this.localisation.pop();
    }

    public void resetLocalisation() {
        while (!(this.localisation.peek() instanceof Salle)) {
            this.localisation.pop();
        }
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public void setEnVie(boolean enVie) {
        this.enVie = enVie;
    }

    public boolean getVie() {
        return this.enVie;
    }
}
