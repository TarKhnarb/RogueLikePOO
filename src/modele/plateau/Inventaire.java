package modele.plateau;

public class Inventaire{


    enum Element{

        Cle,
        ALL
    }

    private int[] inventaire;

    public Inventaire(){

        this.inventaire = new int[Element.ALL.ordinal()];
    }

    public int getTaille(){

        return Element.ALL.ordinal();
    }

    public int getInventaire(Element elmt) throws ArrayIndexOutOfBoundsException{

        return inventaire[elmt.ordinal()];
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
}
