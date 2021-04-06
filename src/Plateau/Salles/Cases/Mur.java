package Plateau.Salles.Cases;

import Plateau.Jeu;

public class Mur extends EntiteStatique {

        /****************
         * Constructeur *
         ****************/
    public Mur(Jeu jeu){

        super(jeu);

        this.type = TypeCase.Mur;
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable(){

        return false;
    }
}
