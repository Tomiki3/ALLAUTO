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

public class InteragissableController {
    Joueur joueur;
    View view;
    Jeu jeu;

    public InteragissableController(Joueur j, View v) {
        joueur = j;
        view = v;
    }
    
    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
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
            view.interagir(f, false);
            Scanner scanFenetre = new Scanner(System.in);   // le scan est closed dans la méthode main TODO non ?
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
    
    public void interagir(IA ia) {
        //TODO Faire un arbre des choix avec les répliques possibles de l'IA
    }

    public void interagir(Ordinateur ordi) {
        if (ordi.getVerrouille())
        {
            if (!ordi.getAllumable())
            {
                view.ordiAllume(false);
                return;
            }

            ordi.setVerrouille(false);
            view.ordiAllume(true);
        }
        // Si ordi éteint, essaye de l'allumer --> peut fonctionner ou non
        // Si allumé, donne le choix de se connecter ou d'interagir avec l'IA
    }

    public void interagir(Porte porte) {

        // check qu'une clef est équipée et que c'est bien la bonne clef dans le cas où la porte est fermée
        if (porte.getVerrouille())
        {
            if ((joueur.getInventaire().getObjetEquipe() instanceof Clef) &&
                ((Clef) (joueur.getInventaire().getObjetEquipe())).getNom() == porte.getClef().getNom())
            {
                porte.setVerrouille(false);    // ouvre la porte
                view.ouvrePorte(true);
            }
            else
            {
                view.ouvrePorte(false);
                return;
            }
        }

        Iterator<Salle> it = porte.getSalles().iterator();

        while (it.hasNext())    // parcourt les salles liées à la porte pour trouver celle dans laquelle on doit aller
        {
            Salle room = it.next();

            if (room.getNom() != joueur.getLocalisation().getNom())
            {
                changeSalle(joueur, room);
                view.entreSalle(room.getNom());
                view.examiner(room);
                break;
            }
        }
    }

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
                view.impossible();
                break;
        }
    }

    public void changeSalle(Joueur j, Salle salleFin) {
        j.setLocalisation(salleFin);
        j.setMeuble(null);
    }

    /**
     * Tue le joueur.
     */
    public void killJoueur() {
        joueur.setEnVie(false);
    }
}
