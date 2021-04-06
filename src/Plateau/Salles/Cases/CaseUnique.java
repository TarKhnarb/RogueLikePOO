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
    }

        /***************
         * EteindreFeu *
         ***************/
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

        return true; // TODO à enlever lorsque les capsules seront implémentées
        //return !feu;
    }

        /************
         * ToString *
         ************/
    @Override
    public String toString(){

        return (feu ? "5" : "1");
    }

        /***************
         * GetTypeCase *
         ***************/
    @Override
    public TypeCase getTypeCase(){

        return (feu ? TypeCase.Feu : TypeCase.Unique);
    }
}
