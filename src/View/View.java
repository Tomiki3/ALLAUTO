package View;

import java.util.Iterator;

import Model.*;

public class View {

    public View() {

    }

    public void afficheBvn() {
        System.out.println("Bienvenu dans cette demonstration du jeu 'Cramptman'.");
    }

    public void meubleManquant() {
        System.out.println("Le meuble que vous souhaitez examiner n'est pas présent dans la salle.");
    }
    
    public void objetManquant() {
        System.out.println("L'objet que vous souhaitez examiner n'est pas présent sur le meuble.");                      
    }

    public void pasInteraction() {
        System.out.println("Aucun objet interagissable n'est à votre portée.");
    }

    public void dabordMeuble() {
        System.out.println("Chaque objet est posé sur un meuble.\nVous devez d'abord examiner un meuble avant de vouloir prendre un objet.");
    }

    public void pasInteractionObj() {
        System.out.println("L'objet avec lequel vous souhaitez interagir n'appartient pas au meuble que vous examinez.");
    }

    public void pasPrendre(String nom) {
        System.out.println(nom + " ne contient pas l'objet que vous souhaitez prendre.");
    }

    public void equiper(String obj) {
        String str;
        if (obj == null) {
            str = "L'objet que vous souhaitez équiper n'est pas présent dans votre inventaire.";
        } else {
            str = "Vous tenez " + obj + " en main.";
        }
        System.out.println(str);
    }

    public void quitter(String nom) {
        System.out.println("Vous quittez " + nom + ".");
    }

    public void impossible() {
        System.out.println("Cette action est actuellement impossible à réaliser.");
    }

    public void decrire(Descriptible d) {
        System.out.println(d.getDescription());
    }

    public void mort() {
        System.out.println("Vous êtes mort. Le jeu est fini.");
    }

    public void mauvaisId() {
        System.out.println("Mauvais identifiant");
    }

    public void examiner(Salle s) {
        decrire(s);
        
        System.out.print("La salle contient : ");
        
        Iterator<Meuble> it = s.getMeubles().iterator();
        while (it.hasNext()) {
            System.out.print(it.next().getNom());

            if (it.hasNext())
                System.out.print(", ");
        }
        System.out.println(".");
    }

    // Pour un objet, l'examiner revient à le décrire
    public void examiner(Objet obj) {
        decrire(obj);
    }

    public void examiner(EntiteVivante e) {
        decrire(e);
    }

    public void examiner(Meuble meuble) {
        decrire(meuble);

        // Print l'ensemble des objets posés sur le meuble
        System.out.print("Sur la " + meuble.getNom() + " se trouvent : "); //TODO il y a forcement au moins un objet ?
        Iterator<Objet> it = meuble.getObjets().iterator();
        while (it.hasNext()) {
            System.out.print(it.next().getNom());

            if (it.hasNext())
                System.out.print(", ");
        }
        System.out.println(".");
        
        // Print l'ensemble des interagissables posés sur le meuble
        Iterator<EntiteVivante> newIt = meuble.getInteragissables().iterator();
        if (newIt.hasNext()) {
            System.out.print("On y voit aussi : ");
            while (newIt.hasNext()) {
                System.out.print(newIt.next().getNom());
                
                if (newIt.hasNext())
                System.out.print(", ");
            }
            System.out.println(".");
        }
    }
    
    public void examiner(Inventaire inventaire) {
        decrire(inventaire);

        Iterator<Objet> it = inventaire.getObjets().iterator();
        System.out.print("\nVoici ce qu'il contient : ");
        while (it.hasNext())
        {
            Objet curr = it.next();
            System.out.print(curr.getNom());
            if (it.hasNext()) {System.out.print(", ");}
        }
        System.out.println(".");

        if (inventaire.getObjetEquipe() != null)
            System.out.println("\nVous tenez " + inventaire.getObjetEquipe().getNom() + " en main.");
    }

    public void examiner(Contenant contenant) {
        decrire(contenant);

        if (contenant.getVerrouille())
        {
            System.out.println("Malheureusement, ce/cette " + contenant.getNom() + " est fermé(e).\n" + //
                               "Vous ne pouvez pas en voir le contenu." );
            return;
        }
        
        System.out.print("Dans ce/cette " + contenant.getNom() + " se trouvent : ");
        
        Iterator<Objet> it = contenant.getObjets().iterator();
        while (it.hasNext()) {
            System.out.print(it.next().getNom());

            if (it.hasNext())
                System.out.print(", ");
        }
        System.out.println(".");
    }

    public void prendre(String nom) {
        System.out.println(nom + " a été ajouté à votre inventaire.");
    }

    public void montrerInventaire(Joueur joueur) {
        System.out.println(joueur.getInventaire());
    }

    public void interagir(Carton c, int cas) {
        String str;
        switch(cas) {
            case 1:
                str = "Vous mettez un grand coup de lame dans le carton et reussissez à l'ouvrir.";
                break;
            case 2:
                str = "Le carton est déjà ouvert. Il semble rempli d'objets mystérieux.";
                break;
            default:
                str = "Vous n'arrivez pas à ouvrir le carton." + //
                    "Peut être devriez vous être plus ... incisif.";
                break;
        }
        System.out.println(str);
    }

    public void interagir(Fenetre f, Boolean verrouille) {
        String str;
        if (verrouille) {
            str = "Vous ouvrez la fenêtre.";
        } else {
            str = "Etes vous sûr de vouloir en finir ?";
        }
        System.out.println(str);
    }

    public void suicide(Boolean voulu) {
        String str;
        if (voulu) {
            str = "Vous sautez dans le vide.\n" + //
            "Vous traversez une crise suicidaire ? \n" + //
            "Ne restez pas seul, rapprochez-vous de vos proches.\n" + //
            "Consultez votre médecin traitant : en fonction de votre situation il pourra vous orienter en urgence vers un spécialiste en psychiatrie ou un psychologue, ou vous adresser au centre médico-psychologique (CMP) dont vous dépendez.\n" + //
            "Réclamez l’aide de spécialistes (psychiatre, psychologue) ou de structures de soins (centré médico-psychologique).\n" + //
            "Contactez des structures d’écoute et de soutien en complément d’une aide médicale.\n" + //
            "Appelez le 3114.\n" + //
            "Appelez les urgences (le 15 si vous êtes en France, le 112 si vous voyagez dans l’Union européenne, le 114 si vous êtes sourd ou malentendant).\n";
        } else {
            str = "Vous avez fait le bon choix.";
        }
        System.out.println(str);

    }
}
