package Plateau.Salles.Cases;

import Plateau.Hero.Inventaire;
import Plateau.Jeu;

public class CaseCle extends EntiteStatique{

        /*************
         * Variables *
         *************/
    private boolean cle;
    private int seed;

        /****************
         * Constructeur *
         ****************/
    public CaseCle(Jeu jeu, int seed){

        super(jeu);

        this.seed = seed;
        this.cle = randomCle();
        this.type = (this.cle ? TypeCase.Cle : TypeCase.Normale);
    }

        /*************
         * RandomCle *
         *************/
    private boolean randomCle(){

        int rand = (int)(Math.random()*5);
        return (((seed%(rand + 1)) + 1)%2 == 1); // 3 chances/5
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

        /**************
         * UpdateCase *
         **************/
    @Override
    public boolean updateCase() throws Exception {

        getCle();

        return true;
    }
}
