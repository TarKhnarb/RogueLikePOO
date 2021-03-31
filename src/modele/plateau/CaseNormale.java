package modele.plateau;

public class CaseNormale extends EntiteStatique{

    public CaseNormale(Jeu jeu){

        super(jeu);
    }

    @Override
    public boolean traversable(){

        return true;
    }
}
