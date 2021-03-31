package modele.plateau;

/**
 * Ne bouge pas (murs...)
 */
public abstract class EntiteStatique{

        /*************
         * Variables *
         *************/
    protected Jeu jeu;

        /****************
         * Constructeur *
         ****************/
    public EntiteStatique(Jeu jeu){

        this.jeu = jeu;
    }

        /***************
         * Traversable *
         ***************/
    public abstract boolean traversable();
}