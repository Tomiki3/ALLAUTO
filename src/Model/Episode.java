package Model;


/**
 * Cette classe donne la structure d'un épisode.
 * On suppose qu'un épisode se termine lorsque le joueur quitte la localisation contfin.
 */
public class Episode {
    private Salle salledep;
    private Localisation contfin;

    public Episode(Salle salldep, Localisation contfin){
        this.salledep = salldep;
        this.contfin = contfin;
    }

    public Salle getSalledep(){
        return salledep;
    }

    public Localisation getLocfin(){
        return contfin;
    }
}
