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
    }

        /**********
         * GetCle *
         **********/
    private void getCle() throws Exception{

        if(cle){

            jeu.getHeros().getInventaire().ajouterNElement(Inventaire.Element.Cle, 1);
            cle = false;
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

        /************
         * ToString *
         ************/
    @Override
    public String toString(){

        return (cle ? "5" : "1");
    }

        /***************
         * GetTypeCase *
         ***************/
    @Override
    public TypeCase getTypeCase(){

        return (cle ? TypeCase.Cle : TypeCase.Normale);
    }


}
