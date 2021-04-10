package Plateau;

import Outils.Coordonnee;
import Outils.Position;
import Plateau.Hero.Heros;
import Plateau.Salles.Cases.EntiteStatique;
import Plateau.Salles.Partie;

import java.io.FileNotFoundException;
import java.util.Observable;

public class Jeu extends Observable implements Runnable{

        /*************
         * Variables *
         *************/
    public static final Coordonnee SIZE = new Coordonnee(21, 11);
    public static final int NBCAPSULES = 3;
    public static final int NBCLE = 2;

    private final int PAUSE = 10; // période de rafraichissement

    private boolean restart;

    private Heros heros;
    private Partie partie;

        /*****************
         * Constructeurs *
         *****************/
    public Jeu(){

        this.restart = false;
        nouveauHero();
        initialisationDesEntites();
    }

    public Jeu(Jeu jeu){

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
         * GetEntite *
         *************/
	public EntiteStatique getEntite(int x, int y) throws Exception{

		if((x < 0) || (x >= SIZE.x) || (y < 0) || (y >= SIZE.y)){

			// L'entité demandée est en-dehors de la grille
			return null;
		}

        return partie.getSalle().getGrille()[x][y];
    }

        /*************
         * GetEntite *
         *************/
    public EntiteStatique getEntite(Coordonnee coordonnee) throws Exception{

        if((coordonnee.x < 0) || (coordonnee.x >= SIZE.x) || (coordonnee.y < 0) || (coordonnee.y >= SIZE.x)){

            // L'entité demandée est en-dehors de la grille
            return null;
        }

        return partie.getSalle().getGrille()[coordonnee.x][coordonnee.y];
    }

        /***************
         * NouveauHero *
         ***************/
    private void nouveauHero(){

        heros = new Heros(this, new Position(new Coordonnee(10, 5), new Coordonnee(0, 0), new Coordonnee(SIZE.x - 1, SIZE.y - 1)));
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

        /***********
         * Restart *
         ***********/
    public void restart(){

        restart = true;
        nouveauHero();
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

        // TODO mettre en place un micro menu pour commencer la partie

        while(partie.partieEnCours()){

            setChanged();
            notifyObservers();

            if(restart){

                restart = false;
                break;
            }

            try{

                Thread.sleep(PAUSE);
            }
            catch(InterruptedException e){

                e.printStackTrace();
            }
        }

        // TODO mettre un message "Bien jouer""

        System.out.println("Partie terminée");
        initialisationDesEntites();
        nouveauHero();
        start();

    }
}
