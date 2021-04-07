package Plateau.Salles.Cases;

import Plateau.Hero.Inventaire;
import Plateau.Jeu;

public class CaseNormale extends EntiteStatique{

        /*************
         * Variables *
         *************/
    private boolean cle;
    private int seed;

        /*****************
         * Constructeurs *
         *****************/
    public CaseNormale(Jeu jeu){

        super(jeu);

        this.cle = false;

        this.type = TypeCase.Normale;
    }

    public CaseNormale(Jeu jeu, boolean cle, int seed){

        super(jeu);

        this.seed = seed;
        this.cle = (cle && randomCle());
        this.type = (this.cle ? TypeCase.Cle : TypeCase.Normale);
    }

        /*************
         * RandomCle *
         *************/
    private boolean randomCle(){

        return (((seed%(Math.random()*5 + 1)) + 1)%2 == 1); // 3 chances/5
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
