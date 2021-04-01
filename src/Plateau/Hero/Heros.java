/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plateau.Hero;

import Plateau.Direction;
import Plateau.Jeu;

/**
 * Héros du jeu
 */
public class Heros{

        /*************
         * Variables *
         *************/
    private int x;
    private int y;

    private Jeu jeu;
    private Inventaire inventaire;
    private Direction direction;

        /****************
         * Constructeur *
         ****************/
    public Heros(Jeu jeu, int x, int y){

        this.x = x;
        this.y = y;
        this.jeu = jeu;

        this.inventaire = new Inventaire();
        this.direction = Direction.Bas;
    }

        /********
         * GetX *
         ********/
    public int getX(){

        return x;
    }

        /********
         * GetY *
         ********/
    public int getY(){

        return y;
    }

        /*****************
         * GetInventaire *
         *****************/
    public Inventaire getInventaire(){

        return inventaire;
    }

        /****************
         * GetDirection *
         ****************/
    public Direction getDirection(){

        return direction;
    }
        /********
         * Haut *
         ********/
    public void haut() throws Exception{

        if(traversable(x, y-1)){

            --y;
            direction = Direction.Haut;
        }
    }

        /**********
         * Droite *
         **********/
    public void droite() throws Exception{

        if(traversable(x+1, y)){

            ++x;
            direction = Direction.Droite;
        }
    }

        /*******
         * Bas *
         *******/
    public void bas() throws Exception {

        if(traversable(x, y+1)){

            ++y;
            direction = Direction.Bas;
        }
    }

        /**********
         * Gauche *
         **********/
    public void gauche() throws Exception{

        if(traversable(x-1, y)){

            --x;
            direction = Direction.Gauche;
        }
    }

        /***************
         * Traversable *
         ***************/
    private boolean traversable(int x, int y) throws Exception{

        if((x > 0) && (x < jeu.SIZE_X) && (y > 0) && (y < jeu.SIZE_Y)){

            return jeu.getEntite(x, y).traversable();
        }
        else{

            return false;
        }
    }
}
