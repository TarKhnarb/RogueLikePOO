package Plateau.Salles.Cases;

import Plateau.Hero.Inventaire;
import Plateau.Jeu;
import VueControleur.VueInventaire;

public class Coffre extends EntiteStatique{

        /*************
         * Variables *
         *************/
    private EtatSerrure etat;
    private Inventaire inventaire;
    private int seed;

        /****************
         * Constructeur *
         ****************/
    public Coffre(Jeu jeu, EtatSerrure etat, int seed){

        super(jeu);

        this.etat = etat;
        this.seed = seed;
        this.type = TypeCase.Coffre;

        remplirAleatoirementCoffre();
    }

        /******************************
         * RemplirAleatoirementCoffre *
         ******************************/
    private void remplirAleatoirementCoffre() {

        inventaire = new Inventaire();
        for(int i = 0; i < Inventaire.Element.All.ordinal(); ++i){

            try{

                int nb = (int) ((seed%(Math.random()*3 + 1)) + 1);
                inventaire.ajouterNElement(Inventaire.Element.values()[i], nb);
            }
            catch (Exception e){

                e.printStackTrace();
            }
        }
    }

        /***********
         * GetEtat *
         ***********/
    public EtatSerrure getEtat(){

        return etat;
    }

        /****************
         * OuvrirCoffre *
         ****************/
    public boolean ouvrirCoffre() throws Exception{

        if(etat.equals(EtatSerrure.Ferme)){

            if(jeu.getHeros().getInventaire().getInventaire(Inventaire.Element.Cle) > 0){

                this.jeu.getHeros().getInventaire().enleverNElement(Inventaire.Element.Cle, 1);
                etat = EtatSerrure.Ouvert;

                this.type = TypeCase.CoffreOuvert;
            }
        }

        return true;
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable() throws Exception{

        return ouvrirCoffre();
    }

        /**************
         * UpdateCase *
         **************/
    @Override
    public boolean updateCase() throws Exception{

        if((inventaire.nombreObjets() != 0) && (etat.equals(EtatSerrure.Ouvert))){

            new VueInventaire(jeu, inventaire);
        }

        return true;
    }
}
