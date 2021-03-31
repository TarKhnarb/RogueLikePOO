package modele.plateau;

public class Mur extends EntiteStatique{

    public Mur(Jeu jeu){

        super(jeu);
    }

    @Override
    public boolean traversable(){

        return false;
    }
}
