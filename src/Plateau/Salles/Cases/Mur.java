package Plateau.Salles.Cases;

import Plateau.Jeu;

public class Mur extends EntiteStatique {

        /****************
         * Constructeur *
         ****************/
    public Mur(Jeu jeu){

        super(jeu);
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable(){

        return false;
    }

        /************
         * ToString *
         ************/
    @Override
    public String toString() {

        return "0";
    }

        /***************
         * GetTypeCase *
         ***************/
    @Override
    public TypeCase getTypeCase() {

        return TypeCase.Mur;
    }
}
