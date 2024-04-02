import java.util.Scanner;

public class Jeu {
    
    public static void main(String[] args) {

        Savon savon = new Savon(2);
        savon.setDescription("Ce savon à l'air vieux." +
        "Personnellement, je ne le toucherai pas même avec un baton.");

        Table table = new Table(1);
        table.setDescription("C'est une jolie table en bois, machallita");
        table.addObjet(savon);
        
        Fenetre fenetre = new Fenetre(0, true);
        fenetre.setDescription("Une fenêtre commune. Il ne vous viendrait jamais à l'idée de sauter au travers.");

        Mur mur = new Mur(0);
        mur.setDescription("Un mur blanc et neuf, décoré de quelques posters.");
        mur.addEntitViv(fenetre);

        Porte porte = new Porte(0, true);
        porte.setDescription(null);

        Salle salle1 = new Salle(0, "Salle n°1");
        salle1.setDescription("La salle de démarrage du jeu. Elle est insipide, comme ta vie.");
        salle1.addMeuble(table);
        salle1.addMeuble(mur);

        Salle salle2 = new Salle(0, "Salle n°2");
        salle2.setDescription("La seconde salle du jeu.\nElle est un peu plus lumineuse que la précédente, mais reste tout de même assez placide.");

        Joueur moi = new Joueur(salle1);

        Scanner scan = new Scanner(System.in);
        System.out.println("Bienvenu dans cette demonstration du jeu 'Cramptman'.");
        moi.getLocalisation().examiner();
        
        
        
        
        while(moi.getVie()) {
            System.out.print("\nVeuillez réaliser une action : ");
            String commande = scan.nextLine();
            String[] arrCommande = commande.split(" ");
            System.out.println("");


            switch(arrCommande[0]) {

               case "examiner":

                    // Si pas de meuble courant, on s'attend à ce que l'entité à examiner soit un meuble
                    if(moi.getMeuble() == null) 
                    {                  
                        String nomMeuble = arrCommande[1];
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
                        String nomExaminable = arrCommande[1];
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

                    if (monMeuble.containsViv(arrCommande[1]) == null)
                    {
                        System.out.println("L'objet avec lequel vous souhaitez interagir n'appartient pas au meuble que vous examinez.");
                        break;
                    }

                    monMeuble.containsViv(arrCommande[1]).interagir(moi);
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
