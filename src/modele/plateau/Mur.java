package modele.plateau;

public class Mur extends EntiteStatique{

        /****************
         * Constructeur *
         ****************/
    public Mur(Jeu jeu){

        super(jeu);
    }

        /***************
         * Traversable *
         ***************/
    @Override
    public boolean traversable(){

        return false;
    }
}
