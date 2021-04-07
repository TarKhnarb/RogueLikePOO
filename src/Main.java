import Plateau.Jeu;
import VueControleur.VueControleur;

public class Main{


        /********
         * Main *
         ********/
    public static void main(String[] args){

        Jeu jeu = new Jeu();
        
        VueControleur vc = new VueControleur(jeu);

        jeu.addObserver(vc);
        
        vc.setVisible(true);

        jeu.start();
    }
}
