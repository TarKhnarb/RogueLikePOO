
import Outils.Taille;
import Plateau.Salles.Etage;
import Plateau.Salles.Partie;
import Plateau.Salles.Salle;
import VueControleur.VueControleur;
import Plateau.Jeu;

import java.io.FileNotFoundException;

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
        jeu.start();

            // Tests d'un étage seul
        /*
        Etage etage = null;
        try{

            etage = new Etage(jeu, 11, 5, 15);
        }
        catch (Exception e){

            e.printStackTrace();
        }

        assert etage != null;
        try{

            etage.genererEtage(0);
        }
        catch (Exception e){

            e.printStackTrace();
        }

        System.out.print(etage.toString());
*/

            // Test d'une partie
        /*
        Partie partie = null;
        try{

            partie = new Partie(jeu);
        }
        catch (FileNotFoundException e){

            e.printStackTrace();
        }

        assert partie != null;
        System.out.println(partie.infoDebug());
        partie.changerEtage();
        partie.changerEtage();
         */
    }
}
