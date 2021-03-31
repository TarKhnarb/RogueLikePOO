package modele.plateau;


public class Inventaire{

        /************************
         * Enumération: Element *
         ************************/
    enum Element{

        Cle,
        test1,
        test2,
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

            inventaire[i] = 0;
        }
    }

        /****************
         * Constructeur *
         ****************/
    public int getTaille(){

        return Element.ALL.ordinal();
    }

        /****************
         * Constructeur *
         ****************/
    public int getInventaire(Element elmt) throws ArrayIndexOutOfBoundsException{

        return inventaire[elmt.ordinal()];
    }

        /****************
         * Constructeur *
         ****************/
    public int getInventaire(int index) throws ArrayIndexOutOfBoundsException{

        return inventaire[index];
    }

        /****************
         * Constructeur *
         ****************/
    public void enleverNElement(Element elmt, int nb) throws Exception{

        if(inventaire[elmt.ordinal()] >= nb){

            inventaire[elmt.ordinal()] -= nb;

        }
        else{

            throw new Exception("Le nombre d'élément à enlever est trop élevé pour : " + elmt);
        }
    }

        /****************
         * Constructeur *
         ****************/
    public void ajouterNElement(Element elmt, int nb) throws Exception{

        if(nb >= 0){

            inventaire[elmt.ordinal()] += nb;
        }
        else{

            throw new Exception("Le nombre d'élémentà ajouter doit être positif ou null");
        }
    }

        /****************
         * Constructeur *
         ****************/
    public void recupererInventaire(Inventaire inventaire) throws Exception{

        int temp;
        for(int i = 0; i < Element.ALL.ordinal(); ++i){

            temp = inventaire.getInventaire(Element.values()[i]);
            this.inventaire[i] += temp;
            inventaire.enleverNElement(Element.values()[i], temp);
        }
    }

        /****************
         * Constructeur *
         ****************/
    public String getNomElement(int i){

        return Element.values()[i].name();
    }
}
