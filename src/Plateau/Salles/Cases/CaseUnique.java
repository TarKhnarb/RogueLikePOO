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
         * EteindreFeu *
         ***************/
    private void eteindreFeu() throws Exception{

        if(feu){

            jeu.getHeros().getInventaire().enleverNElement(Inventaire.Element.Capsule, 1);
            feu = false;
            this.type = TypeCase.Normale;
        }
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable(){

        //return true; // TODO à enlever lorsque les capsules seront implémentées
        return !feu;
    }

    @Override
    public boolean updateCase() throws Exception{

        if(feu){

            jeu.getHeros().getInventaire().enleverNElement(Inventaire.Element.Capsule, 1);
            feu = false;
            this.type = TypeCase.Unique;
        }
        else{

            this.type = TypeCase.Feu;
            feu = true;
        }

        return true;
    }
}
