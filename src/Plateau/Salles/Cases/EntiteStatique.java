package Plateau.Salles.Cases;

import Plateau.Jeu;

/**
 * Ne bouge pas (murs...)
 */
public abstract class EntiteStatique{

        /*************
         * Variables *
         *************/
    protected Jeu jeu;

        /****************
         * Constructeur *
         ****************/
    public EntiteStatique(Jeu jeu){

        this.jeu = jeu;
    }

        /***************
         * Traversable *
         ***************/
    public abstract boolean traversable() throws Exception;

        /************
         * ToString *
         ************/
    public abstract String toString();

        /***************
         * GetTypeCase *
         ***************/
    public abstract TypeCase getTypeCase();
}