package Plateau.Salles.Cases;

import Plateau.Jeu;

public class CaseUnique extends EntiteStatique{

        /*************
         * Variables *
         *************/
    private boolean feu;

        /****************
         * Constructeur *
         ****************/
    public CaseUnique(Jeu jeu, boolean feu){

        super(jeu);

        this.feu = feu;
        this.type = (feu ? TypeCase.Feu : TypeCase.Unique);
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable(){

        return !feu;
    }

        /**************
         * UpdateCase *
         **************/
    @Override
    public boolean updateCase() throws Exception{

        if(feu){

            feu = false;
            this.type = TypeCase.Unique;
        }
        else{

            feu = true;
            this.type = TypeCase.Feu;
        }

        return true;
    }
}
