package Plateau.Salles.Cases;

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
