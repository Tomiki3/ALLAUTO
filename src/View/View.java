package View;

import java.util.Iterator;

import Model.*;


/**
 * La classe View permet de gérer toute la partie "vue" du modèle MVC.
 * Le jeu se joue via ligne de commande donc toutes les actions sont décrite grâce aux fonctions de la classe View.
 */
public class View {

    private final String Normal = "\u001B[0m";
    private final String Green = "\033[1;32m";

    public View() {

    }

    public void afficheBvn() {
        System.out.println("Bienvenu.e dans cette demonstration du jeu 'Cramptman'.");
    }

    public void choixAction() {
        System.out.print(Green + "\nVeuillez réaliser une action : " + Normal);
    }

    public void newLine() {
        System.out.println("");
    }

    public void help() {
        System.out.println("Rappel de commande\n");
        System.out.println("COMMANDE => DESCRIPTION\n");
        System.out.println("help => donne la liste des commandes");
        System.out.println("examiner self => donne la description de la localisation courante");
        System.out.println("examiner [nom objet] => si possible déplace le personnage devant l'objet et en donne la description");
        System.out.println("examiner [nom répertoire] => permet de naviguer dans les fichiers/répertoires d'un ordinateur");
        System.out.println("interagir [nom objet] => permet d'intéragir avec un objet si possible (ouvrir, allumer, ...)");
        System.out.println("prendre [nom objet] => si c'est possible permet de prendre un objet et de le mettre dans son inventaire");
        System.out.println("inventaire => donne une description de l'inventaire");
        System.out.println("équiper [nom objet] => si l'objet désigné se trouve dans l'inventaire alors l'équipe dans la main (clef pour une porte par exemple)");
        System.out.println("quitter => permet de revenir à la localisation précédente");
        System.out.println("connecter => utile uniquement devant un pc (la commande est alors rappelée)");
        newLine();
    
    }

    public void objetManquant(Localisation l) {
        if (l instanceof Salle) {
            objetManquant((Salle) l);
        }
        else if (l instanceof Meuble) {
            objetManquant((Meuble) l);
        }
        else if (l instanceof EntiteVivante) {
            if (l instanceof Contenant) {
                objetManquant((Contenant) l);
            }
            else if (l instanceof Ordinateur) {
                objetManquant((Ordinateur) l);
            }
            else {
                System.out.println("Il n'y a rien à examiner dans cette interagissable.");
            }
        }
    }

    public void objetManquant(Salle s) {
        System.out.println("Le meuble que vous souhaitez examiner n'est pas présent dans la salle.");
    }
    
    public void objetManquant(Meuble m) {
        System.out.println("L'objet que vous souhaitez examiner n'est pas présent sur le meuble.");                      
    }

    public void objetManquant(Contenant c) {
        System.out.println("L'objet que vous souhaitez examiner n'est pas présent dans le contenant.");                      
    }

    public void objetManquant(Ordinateur o) {
        System.out.println("Le repertoire que vous souhaitez examiner n'est pas présent dans l'ordinateur.");                      
    }

    public void ouvrePorte(Boolean possible) {
        if (possible) {
            System.out.println("Vous ouvrez la porte.");
        } else {
            System.out.println("Vous n'arrivez pas à ouvrir la porte. Elle semble fermée à clef.");
        }
    }

    public void entreSalle(String nom) {
        System.out.println("Vous passez dans la salle : " + nom);
    }

    public void ordiAllume(Boolean possible) {
        if (possible) {
            System.out.println("Vous allumez l'ordinateur.\n");
        } else {
            System.out.println("L'ordinateur est éteint et ne semble pas répondre aux tentatives de démarrage.");
        }
    }

    public void connexionReussie(){
        System.out.println("connexion en cours");
        System.out.println("");
    }

    public void idinvalide(){
        System.out.println("identifiant invalide");
    }

    public void mdpinvalide(){
        System.out.println("mot de passe invalide");
    }

    public void ordiAvantConnexion() {
        System.out.println("il faut être devant un ordinateur pour pouvoir se connecter");
    }

    public void IASalut() {
        System.out.println("Un texte s'affiche : Bienvenu.e à XIMRINE, que puis-je faire pour vous ?");
        System.out.println("Un prompt apparait à l'écran.");
    }

    public void IAlisteQuest(IA ia) {
        System.out.println("voici les questions que vous pouvez poser :");
        
        for (int i = 0; i<ia.getSize(); i++)
        {
            System.out.println("["+i+"]"+" "+ia.getQuestion(i));
        }
    }

    public void IAAction(){
        System.out.print(Green + "Rentrer le numéro de la question à poser ou quitter :" + Normal);
    }

    public void IAQuest(int numQuest, IA ia){

        System.out.println("\n>>" + ia.getQuestion(numQuest));
        newLine();
        System.out.println("IA : " + ia.getReponse(numQuest));
    }

    public void interactionImpossible() {
        System.out.println("Aucun objet interagissable n'est à votre portée.");
    }

    public void dabordMeuble() {
        System.out.println("Chaque objet est posé sur un meuble.\nVous devez d'abord examiner un meuble avant de vouloir prendre un objet.");
    }

    public void InteractionObjImpossible() {
        System.out.println("L'objet avec lequel vous souhaitez interagir n'appartient pas au meuble que vous examinez.");
    }

    public void prendreImpossible(String nom) {
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

    public void actionImpossible() {
        System.out.println("Cette action est actuellement impossible à réaliser.");
        System.out.println("Entrer la commande \"help\" si besoin d'aide");
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

    // permet de rediriger l'appel vers la bonne fonction afin de différentier certains cas non triviaux.
    public void examiner(Descriptible d) {
        if (d instanceof Salle) {
            examiner((Salle) d);
        }
        else if (d instanceof Objet) {
            examiner((Objet) d);
        }
        else if (d instanceof EntiteVivante) {
            if (d instanceof Contenant) {
                examiner((Contenant) d);
            }
            else{
                examiner((EntiteVivante) d);
            }
        }
        else if (d instanceof Meuble) {
            examiner((Meuble) d);
        }
        else if (d instanceof Inventaire) {
            examiner((Inventaire) d);
        }
        else if (d instanceof Contenant) {
            examiner((Contenant) d);
        }
        else if (d instanceof Repertoire) {
            examiner((Repertoire) d);
        }
        else if (d instanceof Fichier) {
            examiner((Fichier) d);
        }
    }

    public void examiner(Salle s) {
        decrire(s);
        
        System.out.print("La salle contient : ");
        
        // donne la liste des éléments présents dans la salle
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

        // donne une liste des éléments de l'inventaire
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
        
        // donne une liste des objets présents dans le contenant.
        Iterator<Objet> it = contenant.getObjets().iterator();
        while (it.hasNext()) {
            System.out.print(it.next().getNom());

            if (it.hasNext())
                System.out.print(", ");
        }
        System.out.println(".");
    }

    public void examiner(Repertoire rep) {
        System.out.println(rep.getDescription());
    }

    public void examiner(Fichier fich) {
        System.out.println(fich.getDescription());
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

    public void finep() {
        System.out.println("Soudain vos forces vous quittent.\n" +
                            "Vous vous endormez sur place sans avoir le temps de réagir.\n"+
                            "\n"+
                            "\n"+
                            "\n"+
                            "Vous vous réveillez calmement dans le coin Sud-Ouest du même bureau carré.\n"+
                            "Fin Episode 1\n");
    }

    public void Remerciements() {
        System.out.println("Merci d'avoir jouer à la démo de ...\n"+
                            "Ce projet à été réalisé dans le cadre du cours \"Analayse, Conception Objet de Logiciel\" par Laurine Corbard, Alexis Mourier et Tom Briand\n"+
                            "En espérant que vous n'ayez pas sauté par la fenêtre.\n");
    }
}
