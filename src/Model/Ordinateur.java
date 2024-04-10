package Model;

public class Ordinateur extends EntiteVivante {
    private IA ia;
    private String identifiant;
    private String motDePasse;
    private boolean allumable;
    private Repertoire bureauOrdi;

    // on utilise l'attribut verouille d'une EntiteVivante pour décrire si l'ordinateur est allumable ou non
    public Ordinateur(String nom, boolean eteint, String identifiant, IA ia, boolean allumable, String mdp, Repertoire bureau) {
        super(nom, eteint); // verrouille == eteint
        this.ia = ia;
        this.identifiant = identifiant;
        this.motDePasse = mdp;
        this.allumable = allumable;
        this.bureauOrdi = bureau;
        this.setDescription("Deux options sont sélectionnables : se connecter ou interagir avec " + ia.getNom() + ".\n"+
                                "Connexion : connecter [identifiant] [mot de passe]\n"+
                                "Il peut ne pas y avoir d'identifiant et de mot de passe");
    }

    public boolean getAllumable() {
        return allumable;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getmdp() {
        return motDePasse;
    }

    public IA getIa() {
        return ia;
    }

    public Repertoire getBureau() {
        return bureauOrdi;
    }

    public Descriptible contains(String nomLoc) {
        if (nomLoc.equals(ia.getNom())){
            return ia;
        }
        return null;
    }
}