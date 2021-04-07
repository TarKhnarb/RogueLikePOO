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
    public Porte(Jeu jeu, Direction direction, EtatSerrure etat){

        super(jeu);

        this.direction = direction;
        this.etat = etat;

        setType(getTypeCase());
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
                setType(TypeCase.PorteOuvert);

                return false;
            }

            return false;
        }
        else if(etat.equals(EtatSerrure.Ouvert)){

            jeu.getPartie().changerSalle(this.direction);

            return true;
        }
        else{

            jeu.getPartie().changerEtage();

            return true;
        }
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable() throws Exception{

        return ouvrirPorte();
    }

        /************
         * ToString *
         ************/
    @Override
    public String toString() {

        switch(etat){

            case Ferme:
                return "3";

            case Ouvert:
                return "8";

            case Etage:
                return "9";

            default:
                System.out.println("Porte::toString - la Porte n'a pas d'etat valides");
                return "!";
        }
    }

        /***************
         * GetTypeCase *
         ***************/
    @Override
    public TypeCase getTypeCase() {

        switch(etat){

            case Ferme:
                return TypeCase.PorteFerme;

            case Ouvert:
                return TypeCase.PorteOuvert;

            case Etage:
                return TypeCase.PorteEtage;

            default:
                System.out.println("Porte::getTypeCase - la Porte n'a pas d'etat valides");
                return TypeCase.All;
        }
    }
}
