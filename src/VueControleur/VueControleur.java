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
import Plateau.Salles.Cases.*;

public class VueControleur extends JFrame implements Observer{

        /*************
         * Variables *
         *************/
    private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)
    private Taille size; //Taille de la grille à afficher

        // icones affichées dans la grille
    private ImageIcon[] iconHero;
    private ImageIcon[] iconCase;
    private ImageIcon[] iconInventaire;

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

                    case KeyEvent.VK_C:
                        try{

                            jeu.getHeros().lancerCapsule();
                        }
                        catch(Exception exception){

                            exception.printStackTrace();
                        }
                        break;

                    case KeyEvent.VK_SPACE:
                        try{

                            jeu.getHeros().sauter();
                        }
                        catch(Exception exception){

                            exception.printStackTrace();
                        }
                        break;

                    case KeyEvent.VK_R:
                        try{

                            jeu.restart();
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

        iconHero = new ImageIcon[Direction.All.ordinal()];
        for(int i = 0; i < Direction.All.ordinal(); ++i) {

            iconHero[i] = chargerIcone("data/Images/Cases/Hero_" + Direction.values()[i].name() + ".png");
        }

        iconCase = new ImageIcon[TypeCase.All.ordinal() + 1];
        for(int i = 0; i <= TypeCase.All.ordinal(); ++i){

            iconCase[i] = chargerIcone("data/Images/Cases/" + TypeCase.values()[i].name() + ".png");
        }

        iconInventaire = new ImageIcon[Inventaire.Element.All.ordinal()];
        for(int i = 0; i < Inventaire.Element.All.ordinal(); ++i){

            iconInventaire[i] = chargerIcone("data/Images/Inventaire/" + Inventaire.Element.values()[i].name() + ".png");
        }
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
        setSize(64*Jeu.SIZE.getX(), 64*Jeu.SIZE.getY() + 64);
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

        inventaireLabel = new JLabel[1][size.getX()];

        for(int i = 1; i < size.getX(); ++i){

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

        for(int i = 1; i < inventaire.getTaille()*2; i= (i+2)){

            inventaireLabel[0][i].setIcon(iconInventaire[(i - 1)/2]);
            inventaireLabel[0][i + 1].setText(String.valueOf(inventaire.getInventaire(Inventaire.Element.values()[(i - 1)/2])));
        }
    }

        /*********************
         * MettreAJourGrille *
         *********************/
    private void mettreAJourGrille(){

        for(int x = 0; x < size.getX(); ++x){

            for(int y = 0; y < size.getY(); ++y){

                TypeCase type = TypeCase.All;
                try{

                    type = jeu.getEntite(x, y).getTypeCase();
                }
                catch (Exception exception) {

                    exception.printStackTrace();
                }

                tabJLabel[x][y].setIcon(iconCase[type.ordinal()]);
            }
        }

        Heros hero = jeu.getHeros();
        tabJLabel[hero.getX()][hero.getY()].setIcon(iconHero[hero.getDirection().ordinal()]);
    }

        /**********
         * Update *
         **********/
    @Override
    public void update(Observable o, Object arg){

        SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){

                        mettreAJourAffichage();
                    }
                }); 

    }
}
