package Plateau.Salles.Cases;

import Plateau.Jeu;

public class CaseVide extends EntiteStatique{

        /****************
         * Constructeur *
         ****************/
    public CaseVide(Jeu jeu){

        super(jeu);
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable(){

        return false;
    }
}
