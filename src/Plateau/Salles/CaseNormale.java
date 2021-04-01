package Plateau.Salles;

import Plateau.Jeu;

public class CaseNormale extends EntiteStatique{

        /****************
         * Constructeur *
         ****************/
    public CaseNormale(Jeu jeu){

        super(jeu);
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable(){

        return true;
    }
}
