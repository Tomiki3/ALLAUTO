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

        Salle salle1 = new Salle(0, "Salle n°1");
        salle1.setDescription("La salle de démarrage du jeu. Elle est insipide, comme ta vie.");
        salle1.addMeuble(table);
        salle1.addMeuble(mur);

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

                    if(moi.getMeuble() == null) {                  
                        String nomMeuble = arrCommande[1];
                        Salle maSalle = moi.getLocalisation();

                        if(maSalle.containsMeuble(nomMeuble) == null){
                            System.out.println("Le meuble que vous souhaitez examiner n'est pas présent dans la salle.");
                            break;
                        }

                        moi.setLocalisation(maSalle.containsMeuble(nomMeuble));
                        moi.getMeuble().examiner();
                    }
                    else{
                        String nomObjet = arrCommande[1];
                        Meuble monMeuble = moi.getMeuble();

                        if(monMeuble.containsObjet(nomObjet) == null){
                            System.out.println("L'objet que vous souhaitez examiner n'est pas présent sur le meuble.");
                            break;
                        }

                        monMeuble.containsObjet(nomObjet).examiner();
                    }

                   break;

                
                case "interagir":
                
                    if (moi.getMeuble() == null) {
                        System.out.println("Aucun objet interagissable n'est à votre portée.");
                        break;
                    }

                    Meuble monMeuble = moi.getMeuble();

                    if (monMeuble.containsViv(arrCommande[1]) == null) {
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
