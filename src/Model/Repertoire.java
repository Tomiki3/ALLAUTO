package Model;

import java.util.HashSet;

public class Repertoire extends Localisation {

    private HashSet<Repertoire> sousReps = new HashSet<Repertoire>();
    private HashSet<Fichier> fichiers = new HashSet<Fichier>();

    public Repertoire(String nom) {
        super(nom);
    }

    @Override
    public Descriptible contains(String nomDesc) {

        for (Repertoire rep : sousReps){
            if (rep.getNom().equals(nomDesc)){
                return rep;
            }
        }

        for (Fichier fich : fichiers){
            if (fich.getNom().equals(nomDesc)){
                return fich;
            }
        }

        return null;
    }

    public void addRep(Repertoire rep){
        sousReps.add(rep);
    }

    public void addFichier(Fichier fich){
        fichiers.add(fich);
    }

    public void setDescription(){
        // description simple d'un répertoire
        String nomSousRep = "sous Répertoires : ";
        for (Repertoire rep : sousReps){
            nomSousRep+=rep.getNom() + ", ";
        }

        String nomFichiers = "fichiers : ";
        for (Fichier fich : fichiers){
            nomFichiers+=fich.getNom() + ", ";
        }

        super.setDescription(this.getNom()+"\n"+nomSousRep+"\n"+nomFichiers);
    }
}