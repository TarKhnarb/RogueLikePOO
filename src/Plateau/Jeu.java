package Plateau;

import Outils.Position;
import Outils.Taille;
import Plateau.Salles.Cases.EntiteStatique;
import Plateau.Hero.Heros;
import Plateau.Salles.Partie;

import java.io.FileNotFoundException;
import java.util.Observable;

public class Jeu extends Observable implements Runnable{

        /*************
         * Variables *
         *************/
    public static final Taille SIZE = new Taille(21, 11);

    private int pause = 10; // période de rafraichissement

    private Heros heros;
    private Partie partie;

        /****************
         * Constructeur *
         ****************/
    public Jeu(){

        nouveauHero();
        initialisationDesEntites();
    }

    public Jeu(Jeu jeu){

        System.out.println("Numero etagenouvelle partie: " + jeu.partie.getNumeroEtage());
        this.heros = jeu.heros;
        initialisationDesEntites();
    }

        /************
         * GetHeros *
         ************/
    public Heros getHeros(){

        return heros;
    }

        /*************
         * GetPartie *
         *************/
    public Partie getPartie(){

        return partie;
    }

        /*************
         * GetGrille *
         *************/
    public EntiteStatique[][] getGrille(){

        try{

            return partie.getSalle().getGrille();
        }
        catch(Exception e){

            e.printStackTrace();
        }

        return null;
    }

        /*************
         * GetEntite *
         *************/
	public EntiteStatique getEntite(int x, int y) throws Exception{

		if((x < 0) || (x >= SIZE.getX()) || (y < 0) || (y >= SIZE.getY())){

			// L'entité demandée est en-dehors de la grille
			return null;
		}

        return partie.getSalle().getGrille()[x][y];
    }

    private void nouveauHero(){

        heros = new Heros(this, new Position(10, 5, new Taille(0, 0), new Taille(SIZE.getX() - 1, SIZE.getY() - 1)));
    }

        /****************************
         * InitialisationDesEntites *
         ****************************/
    private void initialisationDesEntites(){

        try{

            partie = new Partie(this);
        }
        catch(FileNotFoundException e){

            e.printStackTrace();
        }

        partie.changerEtage(); // Chargement du premier etage
    }

        /*********
         * Start *
         *********/
    public void start(){

        new Thread(this).start();
    }

        /*******
         * Run *
         *******/
    public void run(){

        // TODO mettre en place un micro menu avec un choix pour commencer la partie

        while(partie.partieEnCours()){

            setChanged();
            notifyObservers();

            try{

                Thread.sleep(pause);
            }
            catch(InterruptedException e){

                e.printStackTrace();
            }
        }

        System.out.println("Partie terminée");
        initialisationDesEntites();
        start();

        // TODO mettre un message "Bien jouer""
    }
}
