package Controller;

import Model.*;
import View.*;

import java.util.Iterator;
import java.util.Scanner;

public class Jeu {
    static InteragissableController interagissableController;
    static View view;
    
    public static void main(String[] args) {
        Salle salle1 = initSalles();
        Joueur moi = new Joueur(salle1);
        view = new View();
        interagissableController = new InteragissableController(moi, view);
        
        Scanner scan = new Scanner(System.in);
        view.afficheBvn();
        view.examiner(moi.getLocalisation());
        
        // TODO : Patch le problème de la nature des contenants, pck peut pas prendre d'objet dessus si c'est pas des meubles

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
                            view.meubleManquant();
                            break;
                        }

                        moi.setLocalisation(maSalle.containsMeuble(nomMeuble));
                        view.examiner(moi.getMeuble());
                    }
                    else
                    {
                        String nomExaminable = cible;
                        Meuble monMeuble = moi.getMeuble();

                        // on regarde dans un premier temps si l'examinable est un Objet
                        if(monMeuble.containsObjet(nomExaminable) != null)
                        {
                            view.examiner(monMeuble.containsObjet(nomExaminable));
                            break;
                        }

                        // si ce n'est pas le cas, on regarde si c'est une EntitéVivante
                        if (monMeuble.containsViv(nomExaminable) != null)
                        {
                            view.examiner(monMeuble.containsViv(nomExaminable)); // cas !Objet et EntitéVivante
                            break;
                        }
                        view.objetManquant(); // cas !Objet et !EntitéVivante  
                    }

                   break;

                
                case "interagir":
                
                    if (moi.getMeuble() == null)
                    {
                        view.pasInteraction();
                        break;
                    }

                    Meuble monMeuble = moi.getMeuble();

                    if (monMeuble.containsViv(cible) == null)
                    {
                        view.pasInteractionObj();
                        break;
                    }

                    interagissableController.interagir(monMeuble.containsViv(cible));
                    break;


                case "prendre":
                    String objet = cible;
                    if (moi.getMeuble() == null)    // joueur n'examine pas un meuble
                    {
                        view.dabordMeuble();
                        break;
                    }

                    if (moi.getMeuble().containsObjet(objet) != null)   // le meuble contient l'objet
                    {
                        prendre(moi, moi.getMeuble().containsObjet(objet));
                        break;
                    }

                    // l'objet est peut être contenu dans un contenant sur le meuble
                    Iterator<Contenant> it = moi.getMeuble().getContenantIterator();
                    Objet o = null;
                    while(it.hasNext())
                    {
                        Contenant cont = it.next();
                        o = cont.containsObjet(cible);
                        if (o != null)
                        {
                            prendre(moi, o);
                            cont.removeObjet(o);
                            break;
                        }
                    }
                    
                    if (o == null) {
                        view.pasPrendre(moi.getMeuble().getNom());
                    }
                    
                    break;


                case "inventaire":
                    view.examiner(moi.getInventaire());
                    break;

                
                case "équiper":
                    String aEquiper = cible;

                    if (moi.getInventaire().contains(aEquiper) == null)
                    {
                        view.equiper(null);
                        break;
                    }
                    
                    moi.getInventaire().setObjetEquipe(moi.getInventaire().contains(aEquiper));
                    view.equiper(aEquiper);
                    break;


                case "quitter":
                    if (moi.getMeuble() != null) {
                        view.quitter(moi.getMeuble().getNom());
                    }
                    
                    quitter(moi);
                    view.examiner(moi.getLocalisation());
                    break;


               default:
                    view.impossible();
                    break;
            }
        }

        view.mort();
        scan.close();
    }

    public static Salle initSalles() {
        // ------ SALLE 1 --------------------------
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
        
// ------ PORTE 1 / 2 --------------------------

        Salle salle2 = new Salle("Salle n°2");
        salle2.setDescription("La seconde salle du jeu.\nElle est un peu plus lumineuse que la précédente, mais reste tout de même assez placide.");

        Clef clefDouze = new Clef("clef de douze");
        clefDouze.setDescription("Une clef de douze.");
        Clef clefQuatre = new Clef("clef de quatre");
        clefQuatre.setDescription("Une clef de quatre.");
        
        Porte porte = new Porte(false, clefDouze);
        porte.setDescription("Une belle porte en bois de hêtre.");
        porte.setSalles(salle1, salle2);

        table.addObjet(clefDouze);
        table.addObjet(clefQuatre);
        mur.addEntitViv(porte);

// ------ SALLE 2 --------------------------

        Ciseaux ciseaux = new Ciseaux("paire de ciseaux");
        ciseaux.setDescription("Une paire de ciseaux pour gaucher. Quelle hérésie ...");

        Savon savonCarton = new Savon();

        Carton carton = new Carton("carton", false);
        carton.addObjet(savonCarton);

        Etagere etagere = new Etagere("étagère");
        etagere.setDescription("Une grande étagère en marbre blanc.\nY'a du budget ici dis donc !");
        etagere.addObjet(ciseaux);

        Sol sol = new Sol();
        sol.setDescription("Une jolie moquette bien poilue.");
        sol.addEntitViv(carton);

        salle2.addMeuble(etagere);
        salle2.addMeuble(sol);

        return salle1;
    }


    /**
     * Nous fait quitter l'examination du meuble courant
     */
    public static void quitter(Joueur j) {
        j.setMeuble(null);
    }  
    
    public static void seConnecter(Ordinateur ordi, String identifiant) {
        if (ordi.getIdentifiant().equals(identifiant))
        {
            // faire les trucs qu'on peut faire pendant la connection
        }
        else
        {
            view.mauvaisId();
        }
    }
    
    public static void prendre(Joueur j, Meuble m, Objet o) {
        m.removeObjet(o);
        j.getInventaire().addObjet(o);
    }

    public static void equiper(Joueur j, Objet o) {
        j.getInventaire().setObjetEquipe(o);
    }


    public static void prendre(Joueur moi, Objet obj) {
        moi.getInventaire().addObjet(obj);
        moi.getMeuble().removeObjet(obj);
        view.prendre(obj.getNom());
    }
}
