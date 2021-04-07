package Plateau.Salles;

import Outils.Coordonnee;
import Outils.Position;
import Outils.Taille;
import Plateau.Direction;
import Plateau.Jeu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 *   Le fichier data/Parties/Reglages.partie doit être instancié de la façon suivante :
 *   "
 *   entier
 *   entier1 entier2
 *   entier1' entier2'
 *   ...
 *   "
 *   entier: taille de l'étage
 *   entier1: nombre minimal de salle sur l'étage
 *   entier2: nombre maximal de salle sur l'étage
 */

public class Partie{

    /***************************
     * Enumération: InfoPartie *
     ***************************/
    public enum InfoPartie{

        MinSalles,
        MaxSalles,
        All
    }

        /*************
         * Variables *
         *************/
    private Jeu jeu;
    private int numeroEtage;
    private final int maxEtages;  // se détermine en fonction du fichier 'data/Parties/Reglages.partie'
    private Etage etage;
    private Position positionEtage;
    private int tailleEtage;
    private ArrayList<ArrayList<Integer>> informations;

        /****************
         * Constructeur *
         ****************/
    public Partie(Jeu jeu) throws FileNotFoundException{

        this.jeu = jeu;
        this.numeroEtage = -1;
        this.maxEtages = loadInformations();

        centrerPosition();
    }

        /************
         * GetEtage *
         ************/
    public Etage getEtage(){

        return etage;
    }

        /***************
         * NouvelEtage *
         ***************/
    private void nouvelEtage() throws Exception{

        jeu.getHeros().centrerDansSalle();
        positionEtage.centrerDansZone();
        etage = new Etage(jeu, tailleEtage, informations.get(numeroEtage).get(InfoPartie.MinSalles.ordinal()), informations.get(numeroEtage).get(InfoPartie.MaxSalles.ordinal()));
    }

        /****************
         * GenererEtage *
         ****************/
    private void genererEtage() throws Exception{

        System.out.println(numeroEtage);
        etage.genererEtage();
    }

        /****************
         * ChangerEtage *
         ****************/
    public void changerEtage(){

        ++numeroEtage;
        if(partieEnCours()){

            System.out.println("Je change d'étages: " + numeroEtage);
            try{

                nouvelEtage();
                genererEtage();
            }
            catch(Exception e){

                e.printStackTrace();
            }
        }
    }

        /****************
         * ChangerSalle *
         ****************/
    public void changerSalle(Direction direction) throws Exception{

        if(!positionEtage.move(direction)){

            throw new Exception("Partie::changerSalle - VOUS NE POUVEZ PAS CHANGER DE SALLES");
        }

        jeu.getHeros().changerSalle(direction);
    }

        /************
         * GetSalle *
         ************/
    public Salle getSalle() throws Exception{

        return etage.getSalle(positionEtage);
    }

        /******************
         * GetNumeroEtage *
         ******************/
    public int getNumeroEtage(){

        return numeroEtage;
    }

        /******************
         * CenterPosition *
         ******************/
    private void centrerPosition(){

        int centre= (tailleEtage - 1) / 2;
        this.positionEtage = new Position(new Coordonnee(centre, centre), new Taille(0, 0), new Taille(tailleEtage - 1, tailleEtage - 1));
    }

        /********************
         * LoadInformations *
         ********************/
    private int loadInformations() throws FileNotFoundException{

        informations = new ArrayList<>();
        int cpt = 0;

        Scanner scanner = new Scanner(new File("data/Parties/Reglages.partie"));

        if(scanner.hasNextInt()){

            this.tailleEtage = scanner.nextInt();
        }

        while(scanner.hasNextInt()){

            ArrayList<Integer> info = new ArrayList<>();

            for(int i = 0; i < InfoPartie.All.ordinal(); ++i){

                info.add(scanner.nextInt());
            }

            informations.add(cpt, info);
            ++cpt;
        }

        if(informations.size() != cpt){

            System.out.println("Partie::loadInformations - Les données ont été mal lues" + informations.size() + " / " + cpt);
        }

        return cpt;
    }

        /*****************
         * PartieEnCours *
         *****************/
    public boolean partieEnCours(){

        return (numeroEtage != informations.size());
    }

        /*************
         * InfoDebug *
         *************/
    public String infoDebug(){

        String retour = "";

        retour += "Taille étages: " + tailleEtage + "\n";
        retour += "Position dans l'étage: " + positionEtage.getX()+ " / " + positionEtage.getY() + "\n";
        for(int i = 0; i < maxEtages; ++i){

            retour += "Etage n°" + i +"\n";
            for(int j = 0; j < InfoPartie.All.ordinal(); ++j){

                retour += "     " + InfoPartie.values()[i].name() + ": " + informations.get(i).get(j) + "\n";
            }
            retour += "\n";
        }

        return retour;
    }
}
