package Model;


/**
 * Cette classe donne la structure d'un épisode.
 * On suppose qu'un épisode se termine lorsque le joueur quitte la localisation contfin.
 */
public class Episode {
    private Salle salleDep;
    private Localisation contFin;

    public Episode(Salle salldep, Localisation contfin){
        this.salleDep = salldep;
        this.contFin = contfin;
    }

    public Salle getSalleDep(){
        return salleDep;
    }

    public Localisation getLocfin(){
        return contFin;
    }
}
