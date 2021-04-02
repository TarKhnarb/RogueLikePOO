package Plateau.Salles.Cases;

import Plateau.Hero.Inventaire;
import Plateau.Jeu;

public class Coffre extends EntiteStatique{

        /*************
         * Variables *
         *************/
    private EtatSerrure etat;
    private Inventaire inventaire;

        /****************
         * Constructeur *
         ****************/
    public Coffre(Jeu jeu, EtatSerrure etat){

        super(jeu);

        this.etat = etat;

        remplirAleatoirementCoffre();
    }

        /******************************
         * RemplirAleatoirementCoffre *
         ******************************/
    private void remplirAleatoirementCoffre() {

        // TODO faire en sorte que l'inventaire du coffre seremplisse avec des quantités aléatoires
    }

        /***********
         * GetEtat *
         ***********/
    public EtatSerrure getEtat(){

        return etat;
    }

        /*****************
         * GetInventaire *
         *****************/
    //  TODO A adapter suivant commenton gère les coffres
    public Inventaire getInventaire(){

        return inventaire;
    }

        /****************
         * OuvrirCoffre *
         ****************/
    public boolean ouvrirCoffre() throws Exception{

        if(etat.equals(EtatSerrure.Ferme)){

            if(this.jeu.getHeros().getInventaire().getInventaire(Inventaire.Element.Cle) != 0){

                this.jeu.getHeros().getInventaire().enleverNElement(Inventaire.Element.Cle, 1);
                etat = EtatSerrure.Ouvert;

                return true;
            }
            else{

                return false;
            }
        }
        else{

            return true;
        }
    }

        /***************
         * Traversable *
         ***************/
    // TODO Voir si une clé est nécessaire pour ouvir un coffre
    @Override
    public boolean traversable() throws Exception{

        return ouvrirCoffre();
    }
}
