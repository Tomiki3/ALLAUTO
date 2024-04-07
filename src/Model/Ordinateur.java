package Model;

public class Ordinateur extends EntiteVivante {
    private IA ia;
    private String identifiant;
    private String motDePasse;
    private boolean allumable;

    public Ordinateur(String nom, boolean eteint, String identifiant, IA ia, boolean allumable, String mdp) {
        super(nom, eteint); // verrouille == eteint
        this.ia = ia;
        this.identifiant = identifiant;
        this.motDePasse = mdp;
        this.allumable = allumable;
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

}
