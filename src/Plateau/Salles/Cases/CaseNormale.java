package Plateau.Salles.Cases;

import Plateau.Hero.Inventaire;
import Plateau.Jeu;

public class CaseNormale extends EntiteStatique{

        /*************
         * Variables *
         *************/
    private boolean cle;

        /****************
         * Constructeur *
         ****************/
    public CaseNormale(Jeu jeu, boolean cle){

        super(jeu);

        this.cle = cle;

        this.type = (cle ? TypeCase.Cle : TypeCase.Normale);
    }

        /**********
         * GetCle *
         **********/
    private void getCle() throws Exception{

        if(cle){

            jeu.getHeros().getInventaire().ajouterNElement(Inventaire.Element.Cle, 1);
            cle = false;
            this.type = TypeCase.Normale;
        }
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable() throws Exception{

        if(cle){

            getCle();
        }

        return true;
    }
}
