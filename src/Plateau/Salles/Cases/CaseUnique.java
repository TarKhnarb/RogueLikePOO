package Plateau.Salles.Cases;

import Plateau.Hero.Inventaire;
import Plateau.Jeu;

// TODO Voir comment gérer le fait que le Heros soit passé sur la case pour remettre feu à true

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
