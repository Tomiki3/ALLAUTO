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
        System.out.println("\n Veuillez réaliser une action :");
        
        while(moi.getVie()) {
            String commande = scan.nextLine();
            String[] arrCommande = commande.split(" ");


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
                            System.out.println("L'objet que vous souhaitez examiner n'est pas présent dans la salle.");
                            break;
                        }

                        monMeuble.containsObjet(nomObjet).examiner();
                    }

                   break;

               default:
                    break;
            }
        }

    }
}
