package Controller;

import java.util.Iterator;
import java.util.Scanner;

import Model.Carton;
import Model.Clef;
import Model.Digicode;
import Model.EntiteVivante;
import Model.Joueur;
import Model.Lame;
import Model.Ordinateur;
import Model.Porte;
import Model.Salle;
import Model.Fenetre;
import Model.IA;

import View.View;

/**
 * partie controlleur de l'architecture MVC.
 * Permet de manipuler les objets et d'afficher les bonnes informations à l'écran.
 */
public class InteractionController {
    Joueur joueur;
    View view;

    public InteractionController(Joueur j, View v) {
        joueur = j;
        view = v;
    }

    // Joueur en paramètre car une action peut potentiellement modifier l'état du joueur (ex : Fenêtre)
    public void interagir(Carton c) {
        if (c.getVerrouille() && joueur.getInventaire().getObjetEquipe() instanceof Lame)
        {
            c.setVerrouille(false);
            view.interagir(c, 0);
        }
        else if (!c.getVerrouille())
        {
            view.interagir(c, 1);
        }
        else
        {
            view.interagir(c, 2);
        }
    }

    public void interagir(Digicode d) {
        // TODO : initialiser un scan + demander code + si code == secretCode on déverrouille la porte associée; 
    }

    public void interagir(Fenetre f) {

        if (f.getVerrouille())
        {
            f.setVerrouille(false);
            view.interagir(f, true);
        }
        else
        {   
            // interagir fenetre lorsque la fenetre est ouverte permet de sauter à travers.
            // on demande donc confirmation avant d'executer l'interaction
            view.interagir(f, false);
            Scanner scanFenetre = new Scanner(System.in);   // le scan est closed dans la méthode main
            String reponse = scanFenetre.nextLine();   // on attend du joueur qu'il réponde par 'oui' ou par 'non'
            
            if (reponse.equals("oui")) 
            {
                view.suicide(true);
                killJoueur();
            }
            else 
            {
                view.suicide(false);
            }
        }
    }
    
    // interaction avec IA = on ne peut que lui poser les questions qui lui ont été attribuées
    public void interagir(IA ia) {
        Scanner scan = new Scanner(System.in);
        view.IASalut();
        view.IAlisteQuest(ia);
        view.newLine();

        view.IAAction();
        String action = scan.nextLine();
        while (!(action.equals("quitter"))) {
            // on récupère le numéro de la question qu'on veut poser
            int numQuest;
            try 
            {
                numQuest = Integer.valueOf(action);
            }
            catch (NumberFormatException e)
            {
                numQuest = -1;
            }

            // on pose la question correspondante au numéro rentré si ce n'est pas une entrée valide, un message d'erreur apparait
            if (0 <= numQuest && numQuest < ia.getSize())
            {
                view.IAQuest(numQuest, ia);
            }

            view.newLine();
            view.IAAction();
            action = scan.nextLine();
            view.newLine();
        }
    }

    public void interagir(Ordinateur ordi) {
        if (ordi.getVerrouille())
        {
            if (!ordi.getAllumable())
            {
                // si ordi éteint et pas allumable alors on peut rien faire
                view.ordiAllume(false);
                return;
            }
            // sinon on l'allume
            ordi.setVerrouille(false);
            view.ordiAllume(true);
        }
        view.examiner(ordi);
        joueur.setLocalisation(ordi);
        // Si ordi éteint, essaye de l'allumer --> peut fonctionner ou non
        // Si allumé, donne le choix de se connecter ou d'interagir avec l'IA
    }

    // cas du PC sans identifiant ni mot de passe.
    public void connecter(Ordinateur ordi){
        if (ordi.getIdentifiant() == null && ordi.getmdp() == null){
            view.connexionReussie();
            joueur.setLocalisation(ordi.getBureau());
            view.examiner(ordi.getBureau());
        }
        else {
            view.idinvalide();
        }
    };

    // cas du PC avec identifiant et mot de passe.
    public void connecter(Ordinateur ordi, String id, String mdp) {
        Boolean idvalide = (id.equals(ordi.getIdentifiant()));
        Boolean mdpvalide = (mdp.equals(ordi.getmdp()));

        if (idvalide && mdpvalide) {
            view.connexionReussie();
            joueur.setLocalisation(ordi.getBureau());
            view.examiner(ordi.getBureau());
        }
        else {
            if (!(idvalide)) {
                view.idinvalide();
                return;
            }
            else {
                view.mdpinvalide();
            }
        }
    }

    public void interagir(Porte porte) {

        // check qu'une clef est équipée et que c'est bien la bonne clef dans le cas où la porte est fermée
        if (porte.getVerrouille())
        {
            if ((joueur.getInventaire().getObjetEquipe() instanceof Clef) &&
                porte.getClef() != null &&
                ((Clef) (joueur.getInventaire().getObjetEquipe())).getNom() == porte.getClef().getNom())
            {
                porte.setVerrouille(false);    // ouvre la porte
                view.ouvrePorte(true);
            }
            else
            {
                view.ouvrePorte(false);
            }
        }
        
        if (!porte.getVerrouille())
        {
            Iterator<Salle> it = porte.getSalles().iterator();
            joueur.resetLocalisation(); // on sait qu'on change de salle donc on peut vider la pile de localisations
        
            while (it.hasNext())    // parcourt les salles liées à la porte pour trouver celle dans laquelle on doit aller
            {
                Salle room = it.next();

                if (!room.getNom().equals(joueur.getLocalisation().getNom()))
                {
                    changeSalle(joueur, room);
                    view.entreSalle(room.getNom());
                    view.examiner(room);
                    break;
                }
            }
        }
    }

    // redirige vers la bonne fonction d'interaction
    public void interagir(EntiteVivante e) {
        switch(e.getClass().getName()) {
            case "Model.Carton":
                interagir((Carton) e);
                break;
            case "Model.Digicode":
                interagir((Digicode) e);
                break;
            case "Model.Fenetre":
                interagir((Fenetre) e);
                break;
            case "Model.IA":
                interagir((IA) e);
                break;
            case "Model.Ordinateur":
                interagir((Ordinateur) e);
                break;
            case "Model.Porte":
                interagir((Porte) e);
                break;
            default:
                view.actionImpossible();
                break;
        }
    }

    public void changeSalle(Joueur j, Salle salleFin) {
        j.quitterLocalisation();
        j.setLocalisation(salleFin);
    }

    /**
     * Tue le joueur.
     */
    public void killJoueur() {
        joueur.setEnVie(false);
    }
}
