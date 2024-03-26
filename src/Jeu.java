import java.util.Scanner;

public class Jeu {
    
    public static void main(String[] args) {

        Objet savon = new Savon(2);
        savon.setDescription("Ce savon à l'air vieux." +
        "Personnellement, je ne le toucherai pas même avec un baton.");

        Meuble table = new Table(1);
        table.setDescription("C'est une jolie table en bois, machallita");
        table.addObjet(savon);

        Salle salle1 = new Salle(0, "Salle n°1");
        salle1.setDescription("La salle de démarrage du jeu. Elle est insipide, comme ta vie.");
        salle1.addMeuble(table);

        Joueur moi = new Joueur(salle1);

        Scanner scan = new Scanner(System.in);
        System.out.println("Bienvenu dans cette demonstration du jeu 'Cramptman'. \n Veuillez réaliser une action :");
        
        while(true) {
            String commande = scan.nextLine();
            String[] arrCommande = commande.split(" ");

            switch(arrCommande[0]) {

               case "examiner":

                    if(moi.getMeuble().equals(null)) {                        
                        String nomMeuble = arrCommande[1];
                        Salle maSalle = moi.getLocalisation();

                        if(maSalle.containsMeuble(nomMeuble).equals(null)){
                            System.out.println("Le meuble que vous souhaitez examiner n'est pas présent dans la salle.");
                            break;
                        }

                        moi.setLocalisation(maSalle.containsMeuble(nomMeuble));
                        moi.getMeuble().examiner();
                    }
                    else{
                        //faire pareil avec les objets
                    }

                   break;

               default:
                    break;
            }
        }

    }
}
