package Model;

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
