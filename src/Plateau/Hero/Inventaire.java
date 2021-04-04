package Plateau.Hero;



public class Inventaire{

        /************************
         * Enumération: Element *
         ************************/
    public enum Element{

        Cle,
        Capsule,
        ALL
    }
        /*************
         * Variables *
         *************/
    private int[] inventaire;

        /****************
         * Constructeur *
         ****************/
    public Inventaire(){

        this.inventaire = new int[Element.ALL.ordinal()];

        initInventaire();
    }

        /******************
         * InitInventaire *
         ******************/
    public void initInventaire(){

        for(int i = 0; i < Element.ALL.ordinal(); ++i){

            inventaire[i] = 50000;
        }
    }

        /*************
         * GetTaille *
         *************/
    public int getTaille(){

        return Element.ALL.ordinal();
    }

        /*****************
         * GetInventaire *
         *****************/
    public int getInventaire(Element elmt) throws ArrayIndexOutOfBoundsException{

        return inventaire[elmt.ordinal()];
    }

        /*******************
         * EnleverNElement *
         *******************/
    public void enleverNElement(Element elmt, int nb) throws Exception{

        if(inventaire[elmt.ordinal()] >= nb){

            inventaire[elmt.ordinal()] -= nb;

        }
        else{

            throw new Exception("Le nombre d'élément à enlever est trop élevé pour : " + elmt);
        }
    }

        /*******************
         * AjouterNElement *
         *******************/
    public void ajouterNElement(Element elmt, int nb) throws Exception{

        if(nb >= 0){

            inventaire[elmt.ordinal()] += nb;
        }
        else{

            throw new Exception("Le nombre d'élémentà ajouter doit être positif ou null");
        }
    }

        /***********************
         * RecupererInventaire *
         ***********************/
    public void recupererInventaire(Inventaire inventaire) throws Exception{

        int temp;
        for(int i = 0; i < Element.ALL.ordinal(); ++i){

            temp = inventaire.getInventaire(Element.values()[i]);
            this.inventaire[i] += temp;
            inventaire.enleverNElement(Element.values()[i], temp);
        }
    }
}
