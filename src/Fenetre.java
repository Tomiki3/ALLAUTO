import java.util.Scanner;

public class Fenetre extends EntiteVivante {
    public Fenetre(int id, Boolean verrouille) {
        super(id, "fenêtre", true);
        // l'attribut "verrouillé" pour une fenêtre correspond à si elle est fermée ou non
    }

    @Override
    public void interagir(Joueur moi) {

        if (this.verrouille)
        {
            this.verrouille = false;
            System.out.println("Vous ouvrez la fenêtre.");
        }

        else
        {
            System.out.println("Etes vous sûr de vouloir en finir ?");
            Scanner scanFenetre = new Scanner(System.in);   // le scan est closed dans la méthode main
            String reponse = scanFenetre.nextLine();   // on attend du joueur qu'il réponde par 'oui' ou par 'non'

            if (reponse == "oui") 
            {
                System.out.println("Vous sautez dans le vide.\n" + //
                "Vous traversez une crise suicidaire ? \n" + //
                "Ne restez pas seul, rapprochez-vous de vos proches.\n" + //
                "Consultez votre médecin traitant : en fonction de votre situation il pourra vous orienter en urgence vers un spécialiste en psychiatrie ou un psychologue, ou vous adresser au centre médico-psychologique (CMP) dont vous dépendez.\n" + //
                "Réclamez l’aide de spécialistes (psychiatre, psychologue) ou de structures de soins (centré médico-psychologique).\n" + //
                "Contactez des structures d’écoute et de soutien en complément d’une aide médicale.\n" + //
                "Appelez le 3114.\n" + //
                "Appelez les urgences (le 15 si vous êtes en France, le 112 si vous voyagez dans l’Union européenne, le 114 si vous êtes sourd ou malentendant).");
                
                moi.kill();
            }
            
            else 
            {
                System.out.println("Vous avez fais le bon choix.");
            }
        }
    }

    @Override
    public void arreter() {
        // ne fait rien, l'action s'arrête d'elle même
    }  
}
