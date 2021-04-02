package Plateau.Salles.Cases;

import Plateau.Hero.Inventaire;
import Plateau.Jeu;

// TODO Voircomment gérerle faitque le Heros soit passé sur la case pour remettre feu à true

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
    }

    private void eteindreFeu() throws Exception{

        if(feu){

            jeu.getHeros().getInventaire().enleverNElement(Inventaire.Element.Capsule, 1);
            feu = false;
        }
    }
    /***************
     * Traversable *
     ***************/
    @Override
    public boolean traversable(){

        return !feu;
    }
}
