package VueControleur;

import Plateau.Hero.Inventaire;
import Plateau.Jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VueInventaire extends JDialog implements ActionListener{

        /*************
         * Variables *
         *************/
    private JButton recuperer;
    private JButton pasRecuperer;

    private Jeu jeu;
    private Inventaire inventaire;

        /****************
         * Constructeur *
         ****************/
    public VueInventaire(Jeu jeu, Inventaire inventaire){

        this.jeu = jeu;
        this.inventaire = inventaire;
            // Panel de la fenêtre
        this.setTitle("Inventaire coffre");
        this.setSize(350, 100);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Inventaire
        int nbObj = this.inventaire.nombreObjets();
        JPanel panelInv = new JPanel(new GridLayout(1, nbObj*2));
        JLabel[][] inventaireLabel = new JLabel[1][nbObj*2];

        int placementObj = 0;
        for(int i = 0; i < this.inventaire.getTaille(); ++i){

            if(this.inventaire.getInventaire(Inventaire.Element.values()[i]) != 0){


                JLabel label = new JLabel();
                inventaireLabel[0][placementObj] = label;
                inventaireLabel[0][placementObj + 1] = label;

                inventaireLabel[0][placementObj].setIcon(chargerIcone("data/Images/Inventaire/" + Inventaire.Element.values()[i].name() + ".png"));
                inventaireLabel[0][placementObj + 1].setText(String.valueOf(this.inventaire.getInventaire(Inventaire.Element.values()[i])));

                panelInv.add(label);
                ++placementObj;
            }
        }

        panel.add(panelInv);

            // Boutons
        JPanel panelBut = new JPanel(new GridLayout(1, 2));

        recuperer = new JButton("Récupérer");
        pasRecuperer = new JButton("Ne pas récupérer");

        recuperer.addActionListener(this);
        pasRecuperer.addActionListener(this);

        panelBut.add(recuperer);
        panelBut.add(pasRecuperer);

        panel.add(panelBut);

        this.add(panel);
        this.setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent actionEvent){

        Object source = actionEvent.getSource();
        if(source.equals(recuperer)){

            try{

                jeu.getHeros().getInventaire().recupererInventaire(this.inventaire);
                dispose();
            }
            catch(Exception e){

                e.printStackTrace();
            }
        }
        else if(source.equals(pasRecuperer)){

            dispose();
        }
    }
}
