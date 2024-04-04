public class Ordinateur extends EntiteVivante {
    private IA ia;
    private String identifiant;
    private boolean allumable;

    public Ordinateur(String nom, boolean eteint, String identifiant, IA ia, boolean allumable) {
        super(nom, eteint); // verrouille == eteint
        this.ia = ia;
        this.identifiant = identifiant;
        this.allumable = allumable;
    }

    @Override
    public void interagir(Joueur moi) {
        if (verrouille)
        {
            if (!allumable)
            {
                System.out.println("L'ordinateur est éteint et ne semble pas répondre aux tentatives de démarrage.");
                return;
            }

            this.verrouille = false;
            System.out.println("En allumant l'ordinateur, deux options sont sélectionnables : se connecter ou IA.\n" + //
                                "Connexion : connecter [nom_machine] [identifiant]");
        }
        // Si ordi éteint, essaye de l'allumer --> peut fonctionner ou non
        // Si allumé, donne le choix de se connecter ou d'interagir avec l'IA
    }

    public void seConnecter(String identifiant) {
        if (this.identifiant.equals(identifiant))
        {
            // faire les trucs qu'on peut faire pendant la connection
        }
        else
        {
            System.out.println("Mauvais identifiant");
        }
    }

    @Override
    public void arreter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'arreter'");
    }

    @Override
    public void examiner() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'examiner'");
    }  
}
