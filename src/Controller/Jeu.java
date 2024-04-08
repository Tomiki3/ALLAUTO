package Controller;

import Model.*;
import View.*;

import java.util.Iterator;
import java.util.Scanner;

public class Jeu {
    static InteragissableController interagissableController;
    static View view;
    
    public static void main(String[] args) {
        Episode ep = EpisodeInitJeu();
        Joueur moi = new Joueur(ep.getSalledep());
        Localisation contraintefin = ep.getLocfin();
        view = new View();
        interagissableController = new InteragissableController(moi, view);
        
        Scanner scan = new Scanner(System.in);
        view.afficheBvn();
        view.examiner((Salle) moi.getLocalisation());
        
        // TODO : Patch le problème de la nature des contenants, pck peut pas prendre d'objet dessus si c'est pas des meubles

        while(moi.getVie() && !moi.getfinep()) {
            view.choixAction();
            String commande = scan.nextLine();
            String[] arrCommande = commande.split(" ");
            view.newLine();

            String action = arrCommande[0];
            String cible = "";
            for (int i = 1; i < arrCommande.length-1; i++) {
                cible += (arrCommande[i] + " ");
            }
            if (arrCommande.length>1){
                cible += arrCommande[arrCommande.length-1];
            }

            switch(action) {

                case "examiner":

                    if (cible.equals("self")) {
                        view.examiner(moi.getLocalisation());
                        break;
                    }

                    if (moi.getInventaire().contains(cible) != null)
                    {
                        view.examiner(moi.getInventaire().contains(cible));
                        break;
                    }

                    if (moi.getLocalisation().contains(cible) != null)
                    {
                        view.examiner(moi.getLocalisation().contains(cible));
                        
                        if (moi.getLocalisation().contains(cible) instanceof Localisation)
                        {
                            // Verifie que c'est pas une entité vivante à la noix
                            if (!(moi.getLocalisation().contains(cible) instanceof EntiteVivante) ||
                                moi.getLocalisation().contains(cible) instanceof Contenant ||
                                 moi.getLocalisation().contains(cible) instanceof Ordinateur)
                            {
                                     moi.setLocalisation((Localisation) moi.getLocalisation().contains(cible));
                            }
                        }
                        break;
                    }

                    view.objetManquant(moi.getLocalisation());
                    break;

                
                case "interagir":
                
                    if (!(moi.getLocalisation() instanceof Meuble) && !(moi.getLocalisation() instanceof Ordinateur))
                    {
                        view.pasInteraction();
                        break;
                    }
                    
                    if (moi.getLocalisation() instanceof Meuble){
                        Meuble monMeuble = (Meuble) moi.getLocalisation();

                        if (monMeuble.containsViv(cible) == null)
                        {
                            view.pasInteractionObj();
                            break;
                        }

                        interagissableController.interagir(monMeuble.containsViv(cible));
                        break;
                    }

                    else if (moi.getLocalisation() instanceof Ordinateur){
                        Ordinateur monOrdi = (Ordinateur) moi.getLocalisation();

                        if (monOrdi.contains(cible) == null)
                        {
                            view.pasInteractionObj();
                            break;
                        }
                        
                        interagissableController.interagir((IA) monOrdi.contains(cible));
                        view.examiner(moi.getLocalisation());
                        break;
                    }


                case "prendre":
                    String objet = cible;
                    if (!(moi.getLocalisation() instanceof Meuble))    // joueur n'examine pas un meuble
                    {
                        view.dabordMeuble();
                        break;
                    }

                    Meuble meuble = (Meuble) moi.getLocalisation();
                    if (meuble.containsObjet(objet) != null)   // le meuble contient l'objet
                    {
                        prendre(moi, meuble.containsObjet(objet));
                        break;
                    }

                    // l'objet est peut être contenu dans un contenant sur le meuble
                    Iterator<Contenant> it = meuble.getContenantIterator();
                    Objet o = null;
                    while(it.hasNext())
                    {
                        Contenant cont = it.next();
                        o = (Objet) cont.contains(cible);
                        if (o != null)
                        {
                            prendre(moi, o);
                            cont.removeObjet(o);
                            break;
                        }
                    }
                    
                    if (o == null) {
                        view.pasPrendre(meuble.getNom());
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
                    if (!(moi.getLocalisation() instanceof Salle)) {
                        if (moi.getLocalisation() == contraintefin){
                            finep(moi);
                            break;
                        }

                        view.quitter(moi.getLocalisation().getNom());
                        quitter(moi);
                    }

                    view.examiner(moi.getLocalisation());
                    break;

                case "connecter":
                    if (!(moi.getLocalisation() instanceof Ordinateur)) {
                        view.ordiAvantConnexion();
                    }

                    String id, mdp;
                    try {
                        id = cible.split(" ")[0];
                    }
                    catch(Exception e) {
                        id = "";
                    }

                    try{
                        mdp = cible.split(" ")[1];    
                    }
                    catch(Exception e) {
                        mdp = "";
                    }

                    interagissableController.connecter((Ordinateur) moi.getLocalisation(), id, mdp);
                    break;

                default:
                    view.impossible();
                    break;
            }
        }

        view.newLine();
        if (!(moi.getVie())){
            view.mort();
        }
        else if (moi.getfinep()){
            view.finep();
        }
        view.newLine();
        view.Remerciements();
        scan.close();
    }

    public static Episode EpisodeInitTest() {
        // ------ SALLE 1 --------------------------
        Savon savon = new Savon();
        savon.setDescription("Ce savon à l'air vieux." +
        "Personnellement, je ne le toucherai pas même avec un baton.");

        Fichier fichTest = new Fichier("Fichier Test", "pas ouf le fichier");

        Repertoire repTest = new Repertoire("Répertoire de Test");
        repTest.addFichier(fichTest);
        repTest.setDescription();

        Repertoire burOrdi = new Repertoire("Bureau OrdiTest");
        burOrdi.addRep(repTest);
        burOrdi.setDescription();

        IA IAtest = new IA("IATest", false);
        IAtest.setQuestRep("Bonjour?", "Coucou");

        Ordinateur ordiTest = new Ordinateur("OrdiTest", false, "", IAtest, true, "", burOrdi);

        Table table = new Table("table");
        table.setDescription("C'est une jolie table en bois, machallita");
        table.addObjet(savon);
        table.addEntitViv(ordiTest);
        
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
        
        Porte porte = new Porte(true, clefDouze, "porte");
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

        return new Episode(salle1, null);
    }

    public static Episode EpisodeInitJeu(){
        // ------ Bureau Initial --------------------------

        Fichier captureEcranMail = new Fichier("capture d'écran",   "Objet : Réorganisation du personnel suite aux avancées de l'IA\n"+
                                                                        "\n"+
                                                                        "Chère PDG,\n"+
                                                                        "\n"+
                                                                        "Suite aux récents progrès dans le développement de l'intelligence artificielle au sein de l'entreprise, je vous adresse ce mail pour discuter de la réorganisation potentielle de notre personnel.\n"+
                                                                        "Après en avoir discuter avec les comptables, il est devenu évident que certains postes sont désormais devenus obsolètes et pourraient être avantageusement remplacés par des solutions basées sur l'IA.\n"+
                                                                        "\n"+
                                                                        "Je propose que nous tenions une réunion pour examiner de plus près les départements et les fonctions où cette transition pourrait être envisagée.\n"+
                                                                        "Il est impératif que nous prenions des mesures proactives pour assurer une transition en douceur et minimiser les perturbations pour notre personnel.\n"+
                                                                        "\n"+
                                                                        "Je suis disponible pour discuter de ces questions plus en détail à votre convenance.\n"+
                                                                        "\n"+
                                                                        "Cordialement,\n"+
                                                                        "Nicolas Moultou\n"+
                                                                        "[DRH de XIMRINE]\n");

        Fichier lettreDeMenace = new Fichier("lettre de menace",    "La lettre date du 5 janvier.\n"+
                                                                        "Cette lettre exprime le mécontentement des employés de XIMRINE concernant le surmenage et l'exploitation au travail.\n"+
                                                                        "Elle met en garde contre les avancées de l'intelligence artificielle dans l'entreprise, qui menacent les emplois des travailleurs.\n"+
                                                                        "Les employés demandent des garanties pour la préservation de leurs postes et de leurs conditions de travail, menaçant d'actions supplémentaires si des changements ne sont pas apportés.\n");

        Fichier preavisDeGreve = new Fichier("préavis de grêve",    "En ouvrant le fichier, on y découvre un préavis de grêve annoncé pour le 1 juin.\n"+
                                                                        "La raison évoquée est le surmenage au travail dans la société XIMRINE.\n"+
                                                                        "D'autres grèves sont à prévoir dans plusieurs autres domaines d'activités pour dénoncer le développement de l'IA qui menace le travail de beaucoup de gens.\n"+
                                                                        "On y lit que cette grève sera accompagnée d'une manifestation devant les locaux de la société et qu'une entrevue concernant les syndicats et la direction de la société serait la bienvenue.\n");

        Repertoire rapportDeCrise = new Repertoire("rapport de crise");
        rapportDeCrise.addFichier(preavisDeGreve);
        rapportDeCrise.addFichier(lettreDeMenace);
        rapportDeCrise.addFichier(captureEcranMail);
        rapportDeCrise.setDescription();
        
        Repertoire bureauOrdiBurInit = new Repertoire("Bureau de l'ordinateur du bureau initial");
        bureauOrdiBurInit.addRep(rapportDeCrise);
        bureauOrdiBurInit.setDescription();

        IA IABurInit = new IA("IA", false);
        IABurInit.setQuestRep("où suis-je ?", "Vous êtes dans les bureaux de XIMRINE, une start-up travaillant dans l'intelligence artificielle. Elle a été montée en 2023 et compte déjà environ 15 employés !\n"+
                                                    "Ils ont annoncé qu'ils travaillaient actuellement sur un tout nouveau projet qui pourrait faire de XIMRINE un pionnier de l'Intelligence Artificelle.\n");
        IABurInit.setQuestRep("qui es-tu ?", "Je suis ALLAUTO, la première IA créée par XIMRINE. Je les aide dans leurs tâches du quotidien et dans leur travail.\n");

        Ordinateur OrdinateurBurInit = new Ordinateur("Ordinateur", true, "JeanneF", IABurInit, true, "Tonymonbeboujtm84", bureauOrdiBurInit);

        Chaise chaiseBur = new Chaise("chaise côtés bureau", false);
        chaiseBur.setDescription(   "De chaque côté du bureau central se trouve une chaise bleue sur roulette.\n"+
                                    "Elles ont l'air très confortables.\n");

        Chaise chaiseCli = new Chaise("chaise côtés client", false);
        chaiseCli.setDescription(   "De chaque côté du bureau central se trouve une chaise bleue sur roulette.\n"+
                                    "Elles ont l'air très confortables.\n");

        Table bureauBurInit = new Table("bureau central");
        bureauBurInit.setDescription("Le bureau est au centre de la pièce.\n"+
                                        "Il est en verre et est décoré d'un petit pot de fleur et d'une photo d'un enfant brun brandissant fièrement une coupe, sur laquelle on peut lire : Tony F.\n");
        bureauBurInit.addEntitViv(OrdinateurBurInit);
        bureauBurInit.addEntitViv(chaiseCli);
        bureauBurInit.addEntitViv(chaiseBur);

        Etagere etagereBurInit = new Etagere("étagère du bureau");
        etagereBurInit.setDescription(  "Sur l'étagère se trouvent des livres d'informatique, de logique, d'IA...\n"+
                                        "L'étagère est décorée de plusieurs figurines inconnues d'une dizaine de centimètres.\n");

        PostIt postIt1 = new PostIt("morceau de post it gauche");
        postIt1.setDescription( "Sur le bout de post it déchiré, il est écrit :\n"+
                                "JeanneF\n"+
                                "Tonymonbe/\n"+
                                "Il semble que la deuxième ligne est coupée à cause de la déchirure.\n");

        Poubelle poubelleBurInit = new Poubelle("poubelle du bureau");
        poubelleBurInit.setDescription("La poubelle est vide à l'exception d'un bout de post it déchiré au fond de cette dernière.");
        poubelleBurInit.addObjet(postIt1);

        Mur murNordBurInit = new Mur("mur nord du bureau");

        Fenetre fenetreSudBurInit = new Fenetre(true);
        fenetreSudBurInit.setDescription(   "Vous vous approchez de la fenêtre.\n"+
                                            "une poignée permet de l'ouvrir.\n"+
                                            "Il fait beau dehors, le soleil est en train de se coucher, une lumière crépusculaire illumine le ciel.\n"+
                                            "Par la fenêtre vous voyez un quartier des affaires avec plusieurs grandes tours.\n"+
                                            "Vous-même êtes dans une tour assez haute.\n"+
                                            "Vous vous trouvez à plusieurs étages du sol.\n"); 

        Mur murSudBurInit = new Mur("mur sud du bureau");
        murSudBurInit.addEntitViv(fenetreSudBurInit);

        Salle bureauInitial = new Salle("Bureau initial");
        bureauInitial.setDescription("Le bureau est mystérieusement éclairé par une unique ampoule au plafond.\n"+
                                        "Il fait bon, une odeur d'encens embaume la pièce.\n"+
                                        "Sur les murs, des posters représentant des technologies presque futuristes sont accrochés.\n"+
                                        "Le sol est en lino bleu clair et les murs en papier peint brun foncé.\n"+
                                        "Le mur Nord est vitré mais un store vénitien fermé empêche de voir de l'extérieur.\n");                               
        bureauInitial.addMeuble(bureauBurInit);
        bureauInitial.addMeuble(etagereBurInit);
        bureauInitial.addMeuble(poubelleBurInit);
        bureauInitial.addMeuble(murNordBurInit);
        bureauInitial.addMeuble(murSudBurInit);

        // ------ PORTE Bur Init / OpenSpace --------------------------

        Salle openSpace = new Salle("Open space");
        openSpace.setDescription(   "L'open space est plongé dans l'obscurité, éclairé sporadiquement par la lueur des ordinateurs et des lumières d'urgence clignotantes.\n"+
                                    "Un silence oppressant règne dans la pièce.\n"+
                                    "Des bruits lointains, comme le grésillement des néons défectueux ou le léger bourdonnement des machines en veille, contribuent à l'ambiance sinistre et déserte de l'endroit.\n"+
                                    "Quelques plantes en pot fanées sont dispersées çà et là, témoignant d'une tentative passée d'égayer l'ambiance austère de l'open space.\n"+
                                    "Des affiches promotionnelles et des tableaux blancs vides ornent les murs, semblant attendre d'être utilisés pour quelque chose de plus que leur fonction initiale.\n"+
                                    "Au centre de l'open space se trouvent quatre bureaux identiques disposés en carré, chacun avec un ordinateur de bureau et des piles de documents désordonnés.\n"+
                                    "Les chaises sont légèrement déplacées, comme si leurs occupants étaient partis à la hâte.\n"+
                                    "Trois portes fermées à clé sont réparties sur les murs Est et Nord de l'open space.\n");

        Clef clefBurInit = new Clef("clef 1");
        clefBurInit.setDescription("une clef, rien de plus normal.");
        
        Porte porteBurInitOpenSpace = new Porte(true, clefBurInit, "porte en bois");
        porteBurInitOpenSpace.setDescription(   "Vous arrivez devant une porte en bois.\n"+
                                                "Elle est peinte en bleu nuit.\n");
        porteBurInitOpenSpace.setSalles(bureauInitial, openSpace);

        etagereBurInit.addObjet(clefBurInit);
        murNordBurInit.addEntitViv(porteBurInitOpenSpace);

        // ------ Open Space -------------------------------------

        Document documentVert = new Document("document vert");
        documentVert.setDescription(    "Le document est un contrôle de qualité d'un certain projet intitulé \"MCB le robot émotif\".\n"+
                                        "Le contrôle indique que les tests se sont bien déroulés mais qu'il reste encore quelques précisions à apporter au prototype.\n"+
                                        "Une note globale de 8/10 est donné en bas de page et des annotations illisibles sont disposées un peut partout sur le document.\n");

        Document pageDeJournal = new Document("page de journal");
        pageDeJournal.setDescription( "Le titre de la page de journal est : \"XIMRINE : Un projet trop ambitieux ?\"\n"+
                                            "L'article présente la société XIMRINE comme étant l'une des pionnières dans le domaine de l'IA.\n"+
                                            "D'après ce même article, c'est une entreprise à succés qui peut prétendre à un grand avenir.\n"+
                                            "Cependant, l'article continue en mentionnant le dernier projet de l'entreprise, dont le nom est gardé secret pour le moment.\n"+
                                            "Le projet est décrit comme inhumain, immoral et dangereux car il mettrait en péril le fondement même de la société et menacerait le travail de millions de citoyens.\n");

        Ordinateur ordiNord = new Ordinateur("ordinateur Nord", true, null, IABurInit, false, null, null);
                           
        Chaise chaiseNord = new Chaise("chaise nord", false);
        chaiseNord.setDescription(  "C'est une chaise de bureau bleu nuit.\n"+
                                    "Elle est poussée en arrière, comme si son occupant s'était levé rapidement.");

        Table bureauNord = new Table("bureau nord");
        bureauNord.setDescription(  "Sur le bureau se trouve un ordinateur éteint avec un écran noir, tandis que des piles de dossiers et de papiers en désordre s'accumulent à côté.\n"+
                                    "Une tasse de café presque vide repose négligemment sur un sous-verre taché.\n"+
                                    "Une chaise de bureau est poussée en arrière, comme si son occupant s'était levé rapidement.\n");
        bureauNord.addObjet(documentVert);
        bureauNord.addObjet(pageDeJournal);
        bureauNord.addEntitViv(ordiNord);
        bureauNord.addEntitViv(chaiseNord);

        PostIt postItBleu = new PostIt("post it bleu");
        postItBleu.setDescription("Sur ce post it, on peut lire : NE PAS OUBLIER : ETEINDRE MCB.\n");

        PostIt postItOrange = new PostIt("post it orange");
        postItOrange.setDescription("Sur ce post it, on peut lire : IMPORTANT : METTRE DESCRIPTION PRECISE DU PROJET DANS LES ARCHIVES [check]\n");

        IA IAOS = new IA("IAOS", false);
        IAOS.setQuestRep("où suis-je ?", "Vous êtes dans les bureaux de XIMRINE. une start-up travaillant dans l'intelligence artificielle. Elle a été montée en 2023 et compte déjà environ 15 employés !\n"+
                                                    "Ils ont annoncé qu'ils travaillaient actuellement sur un tout nouveau projet qui pourrait faire de XIMRINE un pionnier de l'Intelligence Artificelle.\n");
        IAOS.setQuestRep("qui es-tu ?", "Je suis ALLAUTO, la première IA créée par XIMRINE. Je les aide dans leurs tâches du quotidien et dans leur travail.\n");
        IAOS.setQuestRep("Peux-tu me parler du projet MCB ?",     "Le projet MCB est en effet un projet d'envergure que nous menons avec le plus grand sérieux.\n"+
                                                                        "Cependant, je suis désolé.e, je ne suis pas autorisé.e à divulguer des détails spécifiques sur les projets en cours de développement de l'entreprise.\n"+
                                                                        "Sachez toutefois que notre objectif est toujours de repousser les limites de la technologie pour le bénéfice de l'humanité.\n");
        IAOS.setQuestRep("Peut-tu me parler un peu de la philosophie de XIMRINE en matière d'intelligence artificielle ?",    "Bien sûr.\n"+
                                                                                                                                    "Chez XIMRINE, nous croyons fermement que l'intelligence artificielle a le potentiel de transformer radicalement notre société, en améliorant nos vies et en résolvant des problèmes complexes.\n"+
                                                                                                                                    "Cependant, nous reconnaissons également les défis éthiques et sociaux associés à cette technologie, c'est pourquoi nous mettons un fort accent sur la recherche éthique et responsable.\n"+
                                                                                                                                    "Notre objectif est de développer des IA qui travaillent en symbiose avec l'humanité, tout en respectant les valeurs et les droits fondamentaux.\n");

        Fichier sansNom = new Fichier("fichier sans nom",   "En ouvrant le fichier, on découvre un gros titre : Lettre de Démission.\n"+
                                                                "Dedans on y lit que Mme LITON, aujourd'hui ingénieure chez XIMRINE, aimerait démissionner de son poste.\n"+
                                                                "D'après la lettre les raisons sont multiples : les conditions de travails sont décourageantes, le temps et travail investis dans le nouveau projet ont obligé l'ingénieure à sacrifier sa vie de famille, ainsi que ses ami.e.s.\n"+
                                                                "Une phrase attire l'attention : \"Tout ces sacrifices pour que le prototype ne daigne même pas m'adresser la parole !\"");

        

        Repertoire bureauOrdiSud = new Repertoire("bureau ordinateur sud");
        bureauOrdiSud.addFichier(sansNom);
        bureauOrdiSud.setDescription();

        Ordinateur ordiSud = new Ordinateur("ordinateur sud", false, "", IAOS, true, "", bureauOrdiSud);

        Chaise chaiseSud = new Chaise("chaise sud", false);
        chaiseSud.setDescription(   "C'est une simple chaise de bureau en cuir noir, avec des accoudoirs usés par l'usage.\n"+
                                    "Son coussin est légèrement affaissé, témoignant des longues heures passées par son occupant à travailler.\n");

        Table bureauSud = new Table("bureau sud");
        bureauSud.setDescription(   "L'ordinateur est en veille, affichant une image d'économiseur d'écran.\n"+
                                    "Des notes adhésives sont collées sur le côté de l'écran, contenant des rappels et des idées notées à la hâte.\n"+
                                    "Une lampe de bureau se trouve à côté de l'ordinateur, projetant une faible lueur sur les documents éparpillés.\n"+
                                    "Une photo encadrée d'une famille souriante repose à côté du clavier.\n");
        bureauSud.addObjet(postItBleu);
        bureauSud.addObjet(postItOrange);
        bureauSud.addEntitViv(ordiSud);
        bureauSud.addEntitViv(chaiseSud);

        Document agenda = new Document("agenda");
        agenda.setDescription(      "Sur l'agenda on peut lire : 11h réunion MCB // 15h Donner dossier au Boss // 18h rdv Psychologue.\n"+
                                    "L'agenda est parsemé de petite tâches d'humidité.\n"+
                                    "Comme si un liquide c'était égoutté au dessus.\n"+
                                    "Sur la première page de l'agenda on peut lire M. MIPAIN, et en tout petit en bas de la page : \"bisou de Lison (30/10)\".\n"+
                                    "Sur la page du 30 octobre on y lit : \"ANNIV LISON\"\n");
        
        Repertoire bureauOrdiOuest = new Repertoire("bureau de l'ordinateur ouest");
        bureauOrdiOuest.setDescription("Le bureau de l'ordinateur est vide");

        Ordinateur ordiOuest = new Ordinateur("ordinateur ouest", true, "MIPAIN", IAOS, true, "Lison3010", bureauOrdiOuest);

        Chaise chaiseOuest = new Chaise("chaise ouest", false);
        chaiseOuest.setDescription(     "La chaise du bureau ouest est une chaise pivotante en simili cuir brun, avec des accoudoirs rembourrés et une assise légèrement inclinée.\n"+
                                        "Des marques d'usure sont visibles sur les côtés, suggérant une utilisation régulière et prolongée.\n");

        Table bureauOuest = new Table("bureau ouest");
        bureauOuest.setDescription(     "L'ordinateur est allumé, mais l'écran est verrouillé.\n"+
                                        "Des dossiers soigneusement rangés sont empilés sur le bureau, avec un stylo et un agenda ouvert à côté.\n"+
                                        "Une boîte de mouchoirs usagés se trouve à proximité, témoignant peut-être d'une récente frustration ou confusion.\n"+
                                        "Une plante en pot bien entretenue ajoute une touche de verdure à l'environnement.\n");
        bureauOuest.addObjet(agenda);
        bureauOuest.addEntitViv(ordiOuest);
        bureauOuest.addEntitViv(chaiseOuest);

        Document notesManus = new Document("notes manuscrites");
        notesManus.setDescription(      "Parmi les notes illisibles et incohérente sans contexte se trouvent plusieurs croquis et schémas.\n"+
                                        "Un dessin, fait à première vue par un enfant, attire l'attention.\n"+
                                        "Le dessin met en scène une sorte de robot humanoïde très grand et une petite fille qui jouent sur une moquette bleue.\n");
        
        Document documentPapier = new Document("document papier");
        documentPapier.setDescription("C'est un document avec une grosse tache d'encre bleue dessus, comme si l'imprimante qui devait le délivrer n'avait pas bien fait son travail.");

        Ordinateur ordiEst = new Ordinateur("ordinateur est", true, null, IAOS, false, null, null);

        Chaise chaiseEst = new Chaise("chaise est", false);
        chaiseEst.setDescription(   "C'est une chaise ergonomique en tissu gris, avec un dossier réglable et des roulettes qui semblent encore lisses.\n"+
                                    "Elle donne l'impression d'avoir été récemment utilisée, avec une légère empreinte laissée sur le siège.");

        Table bureauEst = new Table("bureau est");
        bureauEst.setDescription(   "L'ordinateur est éteint, avec une pile de papiers en désordre qui s'étend jusqu'au clavier.\n"+
                                    "Une tasse de café renversée gît à côté de l'ordinateur, laissant une marque brune sur le bureau.\n"+
                                    "Des notes manuscrites sont éparpillées sur la surface de travail, certaines avec des croquis et des schémas.\n"+
                                    "Une photo froissée est coincée entre les pages d'un carnet, montrant un groupe d'amis souriants.\n");
        bureauEst.addObjet(notesManus);
        bureauEst.addObjet(documentPapier);
        bureauEst.addEntitViv(ordiEst);
        bureauEst.addEntitViv(chaiseEst);

        Cutter cutter = new Cutter("cutter");
        cutter.setDescription("C'est un cutter standard rien de plus normal.");

        Clef clefTirr = new Clef("clef de tirroir");
        clefTirr.setDescription(    "C'est une clé toute petite ne permettant pas d'ouvrir une porte.\n"+
                                    "Elle serait certainement utile pour ouvrir un tiroir ou un petit cadenas.\n");

        Etagere etagereOpenSpace = new Etagere("étagère de l'open space");
        etagereOpenSpace.setDescription(    "L'étagère se trouve sur le mur nord de la pièce à côtés de la porte et s'étend jusqu'aux fenêtres du mur Ouest.\n"+
                                            "Elle est étonnemment peu remplie.\n");
        etagereOpenSpace.addObjet(cutter);
        etagereOpenSpace.addObjet(clefTirr);

        Document bouleDePapier = new Document("boule de papier");
        bouleDePapier.setDescription(       "En dépliant la boule de papier, on y découvre un message au recto :\n"+
                                            "\"   ykc khuz sh jhmlalyph\n"+
                                            "    Ql kvpz al whysly   \"\n"+
                                            "Et au dos on y lit : C7\n");

        Poubelle poubelleNO = new Poubelle("poubelle nord ouest");
        poubelleNO.setDescription(      "La poubelle est pleine d'épluchures de fruits et de gobelets en cartons.\n"+
                                        "À côté, sur le sol, il y a une boule de papier froissée, comme si son lanceur n'avait pas eu le courage de la reprendre pour la jeter définitivement.\n");
        poubelleNO.addObjet(bouleDePapier);

        Poubelle poubelleSE = new Poubelle("poubelle sud est");
        poubelleSE.setDescription(  "C'est une petite poubelle noir.\n"+
                                    "Elle a été vidée récemment.\n");

        Porte porteEstSud = new Porte(true, null, "porte en métal");
        porteEstSud.setDescription("La porte est robuste, faite de métal épais, avec des charnières visibles et une serrure solide.\n");

        Mur murEstOS = new Mur("mur est de l'open space");
        murEstOS.addEntitViv(porteEstSud);

        Imprimante imprimanteOS = new Imprimante("imprimante de l'open space", true);
        imprimanteOS.setDescription("l'imprimente est éteinte\n");

        Porte porteNord = new Porte(true, null, "porte robuste");
        porteNord.setDescription("La porte est robuste et imposante, avec des panneaux en bois sombre et une poignée en métal brillant.\n");

        Mur murNordOS = new Mur("mur nord de l'open space");
        murNordOS.addEntitViv(porteNord);
        murNordOS.addEntitViv(imprimanteOS);

        Mur murSudOS = new Mur("mur sud de l'open space");
        murSudOS.addEntitViv(porteBurInitOpenSpace);

        Fenetre fenetreOuestOS = new Fenetre(true);
        fenetreSudBurInit.setDescription(   "Vous vous approchez de la fenêtre.\n"+
                                            "une poignée permet de l'ouvrir.\n"+
                                            "Il fait beau dehors, le soleil est en train de se coucher, une lumière crépusculaire illumine le ciel.\n"+
                                            "Par la fenêtre vous voyez un quartier des affaires avec plusieurs grandes tours.\n"+
                                            "Vous-même êtes dans une tour assez haute.\n"+
                                            "Vous vous trouvez à plusieurs étages du sol.\n");

        Mur murOuestOS = new Mur("mur ouest de l'open Space");
        murOuestOS.addEntitViv(fenetreOuestOS);

        openSpace.addMeuble(bureauNord);
        openSpace.addMeuble(bureauOuest);
        openSpace.addMeuble(bureauEst);
        openSpace.addMeuble(bureauSud);
        openSpace.addMeuble(etagereOpenSpace);
        openSpace.addMeuble(poubelleNO);
        openSpace.addMeuble(poubelleSE);
        openSpace.addMeuble(murEstOS);
        openSpace.addMeuble(murNordOS);
        openSpace.addMeuble(murSudOS);
        openSpace.addMeuble(murOuestOS);

        // ------ PORTE OpenSpace / Salle d'attente  --------------------------
        Salle salleDAttente = new Salle("Salle d'attente");
        salleDAttente.setDescription(   "Vous rentrez dans ce qui ressemble à une salle d'attente.\n"+
                                        "Cette dernière, adjacente à l'open space, est accueillante mais minimaliste.\n"+
                                        "Les murs sont recouverts d'un papier peint mélangeant du rouge et du orange.\n"+
                                        "Des chaises rembourrées sont alignées contre le mur. Elles ont l'air confortables.\n"+
                                        "Un tapis épais recouvre le sol, avec un motif abstrait apportant une nouvelle touche de couleur à l'ensemble.\n"+
                                        "Sur une petite table basse au centre de la pièce, des magazines et quelques brochures sont soigneusement disposés, de quoi se divertir.\n"+
                                        "Une lumière douce émane d'un plafonnier encastré, créant une atmosphère chaleureuse et accueillante dans cet espace d'attente tranquille.\n");

        Clef clefPorteOSSalleAtt = new Clef("clef 2");
        clefPorteOSSalleAtt.setDescription("une nouvelle clef un peu vieille.");
        
        Porte porteOSSA = new Porte(true, clefPorteOSSalleAtt, "vieille porte");
        porteOSSA.setDescription("La porte semble un peu vieille, en bois clair, avec une poignée chromée brillante, des rainures discrètes courant le long du panneau");
        porteOSSA.setSalles(openSpace, salleDAttente);

        murEstOS.addEntitViv(porteOSSA);
        bureauSud.addObjet(clefPorteOSSalleAtt);

        // ------ Salle d'attente --------------------------

        Document revueSci = new Document("revue scientifique");
        revueSci.setDescription(  "En prenant la revu on tombe rapidement sur un article : \"XIMRINE : Leader de l'IA et de l'Innovation\".\n"+
                                    "\n"+
                                    "Cet article met en lumière le rôle central de XIMRINE dans l'avancement de l'intelligence artificielle (IA).\n"+
                                    "En se concentrant sur des projets innovants comme ALLAUTO, XIMRINE a établi sa réputation en tant que pionnier de l'IA.\n"+
                                    "On y apprend que l'entreprise se distingue par son engagement envers la recherche éthique et son objectif constant d'améliorer la vie grâce à la technologie.\n");

        Document magazieDeMode = new Document("magazine de mode");
        magazieDeMode.setDescription(   "Le magazine à pour titre principal : \"Les Tendances Printanières : Florals et Pastels en Vogue\"\n"+
                                        "C'est la parution du 23 mai.\n"+
                                        "\n"+
                                        "Dans cet article de mode, nous explorons les tendances incontournables du printemps, mettant en lumière l'omniprésence des motifs floraux et des tons pastel sur les podiums et dans les rues.\n"+
                                        "Des robes légères aux accessoires délicats, le printemps est synonyme de fraîcheur et de féminité avec ces choix de couleurs et de motifs.\n"+
                                        "L'article propose également des conseils pour intégrer ces tendances dans votre garde-robe, afin d'adopter un look printanier chic et moderne.\n");

        Table tableBasse = new Table("table basse");
        tableBasse.setDescription(      "La table basse de la salle d'attente est en bois de chêne, polie pour une finition lisse et brillante.\n"+
                                        "Son design minimaliste est accentué par des lignes épurées et des angles droits.\n"+
                                        "Au centre de la table repose un plateau en verre, mettant en valeur les magazines et brochures soigneusement disposés en dessous.\n");
        tableBasse.addObjet(revueSci);
        tableBasse.addObjet(magazieDeMode);

        PostIt postIt2 = new PostIt("morceau de post it droit");
        postIt2.setDescription(     "Sur le bout de post it déchiré, on peut déchiffrer la fin d'une suite de caractères :\n"+
                                    "boujtm84\n");

        Poubelle petitePoub = new Poubelle("petite poubelle");
        petitePoub.setDescription("La poubelle est vide à l'exception d'un bout de post it déchiré.");
        petitePoub.addObjet(postIt2);

        Table planteEnPot = new Table("plante en pot");
        planteEnPot.setDescription("Le pot contient une grand plante verte censée égayer la salle de sa tranquilité.");

        Chaise bancDeChaise = new Chaise("banc de chaises", false);
        bancDeChaise.setDescription(    "Le banc de chaises dans la salle d'attente est une rangée de sièges rembourrés, disposés sur une base en métal solide.\n"+
                                        "Les assises sont recouvertes d'un tissu orangé assorti à la moquette du sol, offrant un ensemble harmonieux à l'espace.\n"+
                                        "Des accoudoirs discrets ajoutent un confort supplémentaire.\n");

        Mur murNordAtt = new Mur("mur nord de la salle d'attente");
        murNordAtt.addEntitViv(bancDeChaise);

        Porte porteOuestSA = new Porte( true, null, "porte orange");
        porteOuestSA.setDescription("La porte est en bois. Peinte en orange, elle se fond complètement dans l'ambiance de la pièce.");

        Mur murEstAtt = new Mur("mur est de la salle d'attente");
        murEstAtt.addEntitViv(porteOuestSA);

        Mur murOuestAtt = new Mur("mur ouest de la salle d'attente");
        murOuestAtt.addEntitViv(porteOSSA);

        salleDAttente.addMeuble(tableBasse);
        salleDAttente.addMeuble(petitePoub);
        salleDAttente.addMeuble(planteEnPot);
        salleDAttente.addMeuble(murNordAtt);
        salleDAttente.addMeuble(murEstAtt);
        salleDAttente.addMeuble(murOuestAtt);

        return new Episode(bureauInitial, rapportDeCrise);
    }


    /**
     * Nous fait quitter l'examination du meuble courant
     */
    public static void quitter(Joueur j) {
        j.quitterLocalisation();
    }

    public static void finep(Joueur j) {
        j.setfinep(true);
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
        ((Meuble) moi.getLocalisation()).removeObjet(obj);
        view.prendre(obj.getNom());
    }
}
