package VueControleur;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

import Outils.Taille;
import Plateau.Direction;
import Plateau.Jeu;
import Plateau.Hero.*;
import Plateau.Salles.Cases.CaseNormale;
import Plateau.Salles.Cases.EntiteStatique;
import Plateau.Salles.Cases.Mur;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle (flèches direction, etc.))
 */
public class VueControleur extends JFrame implements Observer{

        /*************
         * Variables *
         *************/
    private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private Taille size; //Taille de la grille à afficher

        // icones affichées dans la grille
    private ImageIcon[] icoHero;
    private ImageIcon icoCaseNormale;
    private ImageIcon icoMur;
    private ImageIcon icoColonne;

    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)
    private JLabel[][] inventaireLabel;

        /****************
         * Constructeur *
         ****************/
    public VueControleur(Jeu jeu){

        size = this.jeu.SIZE;
        this.jeu = jeu;

        chargerLesIcones();
        placerLesComposantsGraphiques();
        ajouterEcouteurClavier();
    }

        /**************************
         * AjouterEcouteurClavier *
         **************************/
    private void ajouterEcouteurClavier(){

        addKeyListener(new KeyAdapter(){ // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC

            @Override
            public void keyPressed(KeyEvent e){

                switch(e.getKeyCode()){  // on regarde quelle touche a été pressée

                    case KeyEvent.VK_LEFT:
                        try{

                            jeu.getHeros().bouger(Direction.Gauche);
                        }
                        catch(Exception exception){

                            exception.printStackTrace();
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        try{

                            jeu.getHeros().bouger(Direction.Droite);
                        }
                        catch(Exception exception){

                            exception.printStackTrace();
                        }
                        break;

                    case KeyEvent.VK_DOWN:
                        try{

                            jeu.getHeros().bouger(Direction.Bas);
                        }
                        catch(Exception exception){

                            exception.printStackTrace();
                        }
                        break;

                    case KeyEvent.VK_UP:
                        try{

                            jeu.getHeros().bouger(Direction.Haut);
                        }
                        catch(Exception exception){

                            exception.printStackTrace();
                        }
                        break;
                }
            }
        });
    }

        /********************
         * ChargerLesIcones *
         ********************/
    private void chargerLesIcones(){

        icoHero = new ImageIcon[4];
        icoHero[0] = chargerIcone("data/Images/Pacman_H.png");
        icoHero[1] = chargerIcone("data/Images/Pacman_D.png");
        icoHero[2] = chargerIcone("data/Images/Pacman_B.png");
        icoHero[3] = chargerIcone("data/Images/Pacman_G.png");
        icoCaseNormale = chargerIcone("data/Images/Vide.png");
        icoMur = chargerIcone("data/Images/Mur.png");
    }

        /****************
         * ChargerIcone *
         ****************/
    private ImageIcon chargerIcone(String urlIcone){

        BufferedImage image = null;

        try{

            image = ImageIO.read(new File(urlIcone));
        }
        catch(IOException ex){

            Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return new ImageIcon(image);
    }

        /*********************************
         * PlacerLesComposantsGraphiques *
         *********************************/
    private void placerLesComposantsGraphiques(){

        setTitle("Roguelike");
        setSize(430, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        placerInventaire(panel);

        placerGrille(panel);

        this.add(panel);
    }

        /********************
         * PlacerInventaire *
         ********************/
    private void placerInventaire(JPanel panel){

        Inventaire inventaire = jeu.getHeros().getInventaire();
        JPanel panelInventaire = new JPanel(new GridLayout(1, inventaire.getTaille()*2));

        inventaireLabel = new JLabel[1][inventaire.getTaille()*2];

        for(int i = 0; i < inventaire.getTaille()*2; ++i){

            JLabel label = new JLabel();
            inventaireLabel[0][i] = label;
            panelInventaire.add(label);
        }

        panel.add(panelInventaire);
    }

        /****************
         * PlacerGrille *
         ****************/
    private void placerGrille(JPanel panel){

        JComponent grilleJLabels = new JPanel(new GridLayout(size.getY(), size.getX())); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[size.getX()][size.getY()];

        for(int y = 0; y < size.getY(); ++y){

            for(int x = 0; x < size.getX(); ++x){

                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }

        panel.add(grilleJLabels);
    }

    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
        /************************
         * MettreAJourAffichage *
         ************************/
    private void mettreAJourAffichage(){

        mettreAJourInventaire();

        mettreAJourGrille();
    }

        /*************************
         * MettreAJourInventaire *
         *************************/
    private void mettreAJourInventaire(){

        Inventaire inventaire = jeu.getHeros().getInventaire();

        for(int i = 0; i < inventaire.getTaille()*2 - 1; i= (i+2)){

            inventaireLabel[0][i].setText(Inventaire.Element.values()[i/2].name());
            inventaireLabel[0][i+1].setText(String.valueOf(inventaire.getInventaire(Inventaire.Element.values()[i/2])));
        }
    }

        /*********************
         * MettreAJourGrille *
         *********************/
    private void mettreAJourGrille(){

        for(int x = 0; x < size.getX(); ++x){

            for(int y = 0; y < size.getY(); ++y){

                EntiteStatique e = jeu.getEntite(x, y);

                if(e instanceof Mur){

                    tabJLabel[x][y].setIcon(icoMur);
                }
                else if(e instanceof CaseNormale){

                    tabJLabel[x][y].setIcon(icoCaseNormale);
                }
            }
        }
        tabJLabel[jeu.getHeros().getX()][jeu.getHeros().getY()].setIcon(icoHero[jeu.getHeros().getDirection().ordinal()]);
    }

        /**********
         * Update *
         **********/
    @Override
    public void update(Observable o, Object arg){

        mettreAJourAffichage();
        /*
        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mettreAJourAffichage();
                    }
                }); 
        */
    }
}
