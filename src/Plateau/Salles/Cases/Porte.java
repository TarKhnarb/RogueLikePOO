package Plateau.Salles.Cases;

import Plateau.Direction;
import Plateau.Hero.Inventaire;
import Plateau.Jeu;

public class Porte extends EntiteStatique{

        /*************
         * Variables *
         *************/
    private Direction direction;
    private EtatSerrure etat;

        /****************
         * Constructeur *
         ****************/
    // TODO Voir si on ne met pas de base les portes fermées
    public Porte(Jeu jeu, Direction direction, EtatSerrure etat){

        super(jeu);

        this.direction = direction;
        this.etat = etat;
    }

        /****************
         * GetDirection *
         ****************/
    public Direction getDirection(){

        return direction;
    }

        /***********
         * GetEtat *
         ***********/
    public EtatSerrure getEtat(){

        return etat;
    }

        /***************
         * OuvrirPorte *
         ***************/
    public boolean ouvrirPorte() throws Exception{

        if(etat.equals(EtatSerrure.Ferme)){

            if(this.jeu.getHeros().getInventaire().getInventaire(Inventaire.Element.Cle) != 0){

                this.jeu.getHeros().getInventaire().enleverNElement(Inventaire.Element.Cle, 1);
                etat = EtatSerrure.Ouvert;

                return false;
            }

            return false;
        }
        else{

            jeu.getPartie().changerSalle(this.direction);
            return true;
        }
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable() throws Exception{

        // TODO Voir quoi choisir entre les deux. voir même si ouvrirPorte() est correcte
        return ouvrirPorte();
        //return (etat.equals(EtatSerrure.Ouvert));
    }

        /************
         * ToString *
         ************/
    @Override
    public String toString() {

        return "3";
    }

        /***************
         * GetTypeCase *
         ***************/
    @Override
    public TypeCase getTypeCase() {

        return TypeCase.Porte;
    }
}
