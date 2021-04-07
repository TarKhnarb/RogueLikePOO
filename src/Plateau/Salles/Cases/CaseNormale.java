package Plateau.Salles.Cases;

import Plateau.Jeu;

public class CaseNormale extends EntiteStatique{

        /****************
         * Constructeur *
         ****************/
    public CaseNormale(Jeu jeu){

        super(jeu);

        this.type = TypeCase.Normale;
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable() throws Exception{

        return true;
    }
}
