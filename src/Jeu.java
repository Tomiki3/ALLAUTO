import java.util.Scanner;

public class Jeu {
    
    public static void main(String[] args) {

        Savon savon = new Savon();
        savon.setDescription("Ce savon à l'air vieux." +
        "Personnellement, je ne le toucherai pas même avec un baton.");

        Table table = new Table();
        table.setDescription("C'est une jolie table en bois, machallita");
        table.addObjet(savon);
        
        Fenetre fenetre = new Fenetre(true);
        fenetre.setDescription("Une fenêtre commune. Il ne vous viendrait jamais à l'idée de sauter au travers.");

        Mur mur = new Mur("Mur nord");
        mur.setDescription("Un mur blanc et neuf, décoré de quelques posters.");
        mur.addEntitViv(fenetre);

        
        Salle salle1 = new Salle("Salle n°1");
        salle1.setDescription("La salle de démarrage du jeu. Elle est insipide, comme ta vie.");
        salle1.addMeuble(table);
        salle1.addMeuble(mur);
        
        Salle salle2 = new Salle("Salle n°2");
        salle2.setDescription("La seconde salle du jeu.\nElle est un peu plus lumineuse que la précédente, mais reste tout de même assez placide.");

        Clef clefDouze = new Clef("clef de douze");
        clefDouze.setDescription("Une clef de douze.");
        Clef clefQuatre = new Clef("clef de quatre");
        clefQuatre.setDescription("Une clef de quatre.");
        
        Porte porte = new Porte(true, clefDouze);
        porte.setDescription("Une belle porte en bois de hêtre.");
        porte.setSalles(salle1, salle2);

        table.addObjet(clefDouze);
        table.addObjet(clefQuatre);
        mur.addEntitViv(porte);

        Joueur moi = new Joueur(salle1);
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Bienvenu dans cette demonstration du jeu 'Cramptman'.");
        moi.getLocalisation().examiner();
        

        while(moi.getVie()) {
            System.out.print("\nVeuillez réaliser une action : ");
            String commande = scan.nextLine();
            String[] arrCommande = commande.split(" ");
            System.out.println("");

            String action = arrCommande[0];
            String cible = "";
            for (int i = 1; i < arrCommande.length-1; i++) {
                cible += (arrCommande[i] + " ");
            }
            cible += arrCommande[arrCommande.length-1];

            switch(action) {

               case "examiner":

                    // Si pas de meuble courant, on s'attend à ce que l'entité à examiner soit un meuble
                    if(moi.getMeuble() == null) 
                    {                  
                        String nomMeuble = cible;
                        Salle maSalle = moi.getLocalisation();

                        if(maSalle.containsMeuble(nomMeuble) == null)
                        {
                            System.out.println("Le meuble que vous souhaitez examiner n'est pas présent dans la salle.");
                            break;
                        }

                        moi.setLocalisation(maSalle.containsMeuble(nomMeuble));
                        moi.getMeuble().examiner();
                    }
                    else
                    {
                        String nomExaminable = cible;
                        Meuble monMeuble = moi.getMeuble();

                        // on regarde dans un premier temps si l'examinable est un Objet
                        if(monMeuble.containsObjet(nomExaminable) != null)
                        {
                            monMeuble.containsObjet(nomExaminable).examiner();
                            break;
                        }

                        // si ce n'est pas le cas, on regarde si c'est une EntitéVivante
                        if (monMeuble.containsViv(nomExaminable) != null)
                        {
                            monMeuble.containsViv(nomExaminable).examiner(); // cas !Objet et EntitéVivante
                            break;
                        }

                        System.out.println("L'objet que vous souhaitez examiner n'est pas présent sur le meuble."); // cas !Objet et !EntitéVivante                        
                    }

                   break;

                
                case "interagir":
                
                    if (moi.getMeuble() == null)
                    {
                        System.out.println("Aucun objet interagissable n'est à votre portée.");
                        break;
                    }

                    Meuble monMeuble = moi.getMeuble();

                    if (monMeuble.containsViv(cible) == null)
                    {
                        System.out.println("L'objet avec lequel vous souhaitez interagir n'appartient pas au meuble que vous examinez.");
                        break;
                    }

                    monMeuble.containsViv(cible).interagir(moi);
                    break;


                case "prendre":
                    String objet = cible;
                    if (moi.getMeuble() == null)    // joueur n'examine pas un meuble
                    {
                        System.out.println("Chaque objet est posé sur un meuble.\nVous devez d'abord examiner un meuble avant de vouloir prendre un objet.");
                        break;
                    }
                    if (moi.getMeuble().containsObjet(objet) == null)   // le meuble ne contient pas l'objet
                    {
                        System.out.println(moi.getMeuble().getNom() + (" ne contient pas l'objet que vous souhaitez prendre."));
                        break;
                    }
                    moi.getMeuble().containsObjet(objet).prendre(moi);
                    break;


                case "inventaire":
                    moi.getInventaire().examiner();
                    break;

                case "équiper":
                    String aEquiper = cible;

                    if (moi.getInventaire().contains(aEquiper) == null)
                    {
                        System.out.println("L'objet que vous souhaitez équiper n'est pas présent dans votre inventaire.");
                        break;
                    }
                    
                    moi.getInventaire().setObjetEquipe(moi.getInventaire().contains(aEquiper));
                    System.out.println("Vous tenez " + aEquiper + " en main.");
                    break;


                case "quitter":
                    if (moi.getMeuble() != null)
                        System.out.println("Vous quittez " + moi.getMeuble().getNom() + ".");
                    
                    moi.quitter();
                    moi.getLocalisation().examiner();
                    break;


               default:
                    System.out.println("Cette action est actuellement impossible à réaliser.");
                    break;
            }
        }

        System.out.println("Vous êtes mort. Le jeu est fini.");
        scan.close();
    }
}
