public class Carton extends Contenant {
    public Carton(String nom, Boolean verrouille) {
        super(nom, verrouille);
    }

    @Override
    public void interagir(Joueur moi) {
        if (verrouille && moi.getInventaire().getObjetEquipe() instanceof Lame)
        {
            this.verrouille = false;
            System.out.println("Vous mettez un grand coup de lame dans le carton et reussissez à l'ouvrir.");
        }
        else if (!verrouille)
        {
            System.out.println("Le carton est déjà ouvert. Il semble rempli d'objets mystérieux.");
        }
        else
        {
            System.out.println("Vous n'arrivez pas à ouvrir le carton." + //
                                "Peut être devriez vous être plus ... incisif.");
        }
    }

    @Override
    public void arreter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'arreter'");
    }
}