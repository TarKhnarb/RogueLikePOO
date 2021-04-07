package Plateau.Salles.Cases;

import Plateau.Jeu;

public abstract class EntiteStatique{

        /*************
         * Variables *
         *************/
    protected Jeu jeu;
    protected TypeCase type;

        /****************
         * Constructeur *
         ****************/
    public EntiteStatique(Jeu jeu){

        this.jeu = jeu;
    }

        /***************
         * Traversable *
         ***************/
    public abstract boolean traversable() throws Exception;

        /************
         * ToString *
         ************/
    public String toString(){

        return String.valueOf(type.ordinal());
    }

        /***************
         * SetTypeCase *
         ***************/
    public void setType(TypeCase type){

        this.type = type;
    }

        /***************
         * GetTypeCase *
         ***************/
    public TypeCase getTypeCase(){

        return type;
    }

        /**************
         * UpdateCase *
         **************/
    public boolean updateCase() throws Exception{

        return false;
    }
}