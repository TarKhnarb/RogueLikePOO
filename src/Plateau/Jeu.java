/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plateau;

import Plateau.Salles.Cases.EntiteStatique;
import Plateau.Hero.Heros;
import Plateau.Salles.Cases.CaseNormale;
import Plateau.Salles.Cases.Mur;

import java.util.Observable;


public class Jeu extends Observable implements Runnable{

        /*************
         * Variables *
         *************/
    public static final int SIZE_X = 21;
    public static final int SIZE_Y = 11;

    private int pause = 200; // période de rafraichissement

    private Heros heros;
    private EntiteStatique[][] grilleEntitesStatiques = new EntiteStatique[SIZE_X][SIZE_Y];

        /****************
         * Constructeur *
         ****************/
    public Jeu(){

        initialisationDesEntites();
    }

        /************
         * GetHeros *
         ************/
    public Heros getHeros(){

        return heros;
    }

        /*************
         * GetGrille *
         *************/
    public EntiteStatique[][] getGrille(){

        return grilleEntitesStatiques;
    }

        /*************
         * GetEntite *
         *************/
	public EntiteStatique getEntite(int x, int y){

		if((x < 0) || (x >= SIZE_X) || (y < 0) || (y >= SIZE_Y)){

			// L'entité demandée est en-dehors de la grille
			return null;
		}

		return grilleEntitesStatiques[x][y];
	}

        /****************************
         * InitialisationDesEntites *
         ****************************/
    private void initialisationDesEntites(){

        heros = new Heros(this, 10, 5);

        // murs extérieurs horizontaux
        for(int x = 0; x < 21; ++x){

            ajouteEntiteStatique(new Mur(this), x, 0);
            ajouteEntiteStatique(new Mur(this), x, 10);
        }

        // murs extérieurs verticaux
        for(int y = 1; y < 10; ++y){

            ajouteEntiteStatique(new Mur(this), 0, y);
            ajouteEntiteStatique(new Mur(this), 20, y);
        }

        ajouteEntiteStatique(new Mur(this), 2, 6);
        ajouteEntiteStatique(new Mur(this), 3, 6);

        for(int x = 0; x < SIZE_X; ++x){

            for(int y = 0; y < SIZE_Y; ++y){

                if(grilleEntitesStatiques[x][y] == null){

                    grilleEntitesStatiques[x][y] = new CaseNormale(this, false);
                }
            }
        }
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

        while(true){

            setChanged();
            notifyObservers();

            try{

                Thread.sleep(pause);
            }
            catch(InterruptedException e){

                e.printStackTrace();
            }
        }
    }

        /*************************
         * AjouterEntiteStatique *
         *************************/
    private void ajouteEntiteStatique(EntiteStatique e, int x, int y){

        grilleEntitesStatiques[x][y] = e;
    }
}
