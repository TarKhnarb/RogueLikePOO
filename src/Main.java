import Plateau.Jeu;
import VueControleur.VueControleur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Main{

    public static void main(String[] args){

        Jeu jeu = new Jeu();
        
        VueControleur vc = new VueControleur(jeu);

        jeu.addObserver(vc);
        
        vc.setVisible(true);

        //while(jeu.getPartie().partieEnCours()){

            jeu.start();
        //}
    }
}
