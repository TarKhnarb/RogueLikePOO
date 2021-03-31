package modele.plateau;

/**
 * Ne bouge pas (murs...)
 */
public abstract class EntiteStatique{

    protected Jeu jeu;

    public EntiteStatique(Jeu jeu){

        this.jeu = jeu;
    }

    public abstract boolean traversable();
}