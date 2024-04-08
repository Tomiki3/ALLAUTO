package Model;

import java.util.Stack;

/**
 * Cette classe donne toutes les informations importantes sur le joueur (Localisation actuelle, inventaire, ...)
 */
public class Joueur {
    private Stack<Localisation> localisation;
    private Inventaire inventaire;
    private boolean enVie;
    private boolean finep;

    public Joueur(Salle salle1) {
        this.localisation = new Stack<>();
        this.localisation.push(salle1);
        this.inventaire = new Inventaire();
        this.enVie = true;
        this.finep = false;
    }
    
    public void setLocalisation (Localisation newLoc) {
        if (newLoc instanceof Salle) {
            this.localisation.push(newLoc);
        }
        else if (this.localisation.peek().contains(newLoc.getNom()) != null) {
            this.localisation.push(newLoc);
        }
        else if (this.localisation.peek() instanceof Ordinateur) {
            if (((Ordinateur) this.localisation.peek()).getBureau() == newLoc){
                this.localisation.push(newLoc);
            }
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

    public boolean getfinep() {
        return this.finep;
    }

    public void setfinep(boolean episodetermine){
        this.finep = episodetermine;
    }
}
