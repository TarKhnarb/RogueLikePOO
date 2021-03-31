package modele.plateau;


public class Inventaire{

    enum Element{

        Cle,
        test1,
        test2,
        ALL
    }

    private int[] inventaire;

    public Inventaire(){

        this.inventaire = new int[Element.ALL.ordinal()];

        initInventaire();
    }

    public void initInventaire(){

        for(int i = 0; i < Element.ALL.ordinal(); ++i){

            inventaire[i] = 0;
        }
    }

    public int getTaille(){

        return Element.ALL.ordinal();
    }

    public int getInventaire(Element elmt) throws ArrayIndexOutOfBoundsException{

        return inventaire[elmt.ordinal()];
    }

    public int getInventaire(int index) throws ArrayIndexOutOfBoundsException{

        return inventaire[index];
    }

    public void enleverNElement(Element elmt, int nb) throws Exception{

        if(inventaire[elmt.ordinal()] >= nb){

            inventaire[elmt.ordinal()] -= nb;

        }
        else{

            throw new Exception("Le nombre d'élément à enlever est trop élevé pour : " + elmt);
        }
    }

    public void ajouterNElement(Element elmt, int nb) throws Exception{

        if(nb >= 0){

            inventaire[elmt.ordinal()] += nb;
        }
        else{

            throw new Exception("Le nombre d'élémentà ajouter doit être positif ou null");
        }
    }

    public void recupererInventaire(Inventaire inventaire) throws Exception{

        int temp;
        for(int i = 0; i < Element.ALL.ordinal(); ++i){

            temp = inventaire.getInventaire(Element.values()[i]);
            this.inventaire[i] += temp;
            inventaire.enleverNElement(Element.values()[i], temp);
        }
    }

    public String getNomElement(int i){

        return Element.values()[i].name();
    }
}
