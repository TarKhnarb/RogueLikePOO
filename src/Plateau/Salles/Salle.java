package Plateau.Salles;

import Outils.Taille;
import Plateau.Direction;
import Plateau.Jeu;
import Plateau.Salles.Cases.*;

import java.io.*;
import java.util.Scanner;

public class Salle{

        /*********************
         * Enumération: Type *
         *********************/
    /*
     * Noter qu'un type de salle se présente sous la forme suivante :
     *      AAAAX
     * A: Lettre représentant le positionnement de la porte dans la salle (cf Direction)
     * X: chiffre représentant les variantes de salles
    */
    public enum Type{

        Debut,
        Fin,
        H,
        D,
        B,
        G,
        HB,
        GD,
        HD,
        DB,
        BG,
        HG,
        HDB,
        DBG,
        HBG,
        HDG,
        HDBG,
        None
    }

        /*************
         * Variables *
         *************/
    private Type type;
    private EntiteStatique[][] grille;
    private Porte[] portes;
    private Taille taille;
    private Jeu jeu;


        /****************
         * Constructeur *
         ****************/
    public Salle(Type type, Taille taille, Jeu jeu){

        this.type = type;
        this.taille = taille;
        this.jeu = jeu;

        this.grille = new EntiteStatique[taille.getX()][taille.getY()];
        this.portes = new Porte[4];
    }


        /************
         * GetClass *
         ************/
    private EntiteStatique getClass(TypeCase type, Direction direction){

        switch(type){

            case Normale:
                return new CaseNormale(jeu, false);

            case Vide:
                return new CaseVide(jeu);

            case Porte:
                return new Porte(jeu, direction, EtatSerrure.Ferme);

            case Coffre:
                return new Coffre(jeu, EtatSerrure.Ouvert);

            case Cle:
                return new CaseNormale(jeu, true);

            case Unique:
                return new CaseUnique(jeu,false);

            case Feu:
                return new CaseUnique(jeu, true);

            case Mur: default:
                return new Mur(jeu);
        }
    }

        /*********************
         * GetDirectionPorte *
         *********************/
    private Direction getDirectionPorte(int x, int y){

        if(x == 0){

            return Direction.Gauche;
        }
        else if(x == taille.getX() - 1){

            return Direction.Droite;
        }
        else if(y == 0){

            return Direction.Haut;
        }
        else if(y == taille.getY() - 1){

            return Direction.Bas;
        }
        else{

            return Direction.All;
        }
    }


        /***************
         * PlacerCases *
         ***************/
    private void placerCases(){

        int x = 0, y = 0;
        try(FileInputStream inputStream = new FileInputStream("data/Salles/Salle_" + this.type.name() + ".salle");
            Scanner scanner = new Scanner(inputStream)){

            scanner.useDelimiter(" \n");

            String valeur;

            while(scanner.hasNext()){

                valeur = scanner.next();
                ++x;
                if(x == taille.getX() - 1){

                    x = 0;
                    ++y;
                }

                grille[x][y] = getClass(TypeCase.values()[Integer.parseInt(valeur)], getDirectionPorte(x, y));
            }
        }
        catch(IOException e){

            e.printStackTrace();
        }
    }

        /*************
         * GetGrille *
         *************/
    public EntiteStatique[][] getGrille(){

        return grille;
    }

        /***********
         * GetType *
         ***********/
    public Type getType(){

        return type;
    }


        /****************
         * AjouterPorte *
         ****************/
    public void ajouterPorte(Direction direction, EtatSerrure etat){

        portes[direction.ordinal()] = new Porte(jeu, direction, etat);
    }

        /****************
         * AffecterType *
         ****************/
    public int affecterType(int seed) throws Exception {

        boolean haut = false;
        boolean droite = false;
        boolean bas = false;
        boolean gauche = false;

        int cpt = 0;

        for(int i = 0; i < 4; ++i){

            if(portes[i] != null){

                switch(i){

                    case 0:
                        haut = true;
                        ++cpt;
                        break;

                    case 1:
                        droite = true;
                        ++cpt;
                        break;

                    case 2:
                        bas = true;
                        ++cpt;
                        break;

                    case 3:
                        gauche = true;
                        ++cpt;
                        break;
                }
            }
        }

        switch(cpt){

            default: // TODO Si lemessage d'erreurarriveune fois mettre en place un try/cath dans Etage::affecterTypeSalles
                throw new Exception("Salle::affecterType - ATTENTION SALLE ISOLÉE DETECTEE");

            case 1:
                if(haut){

                    type = Type.H;
                }
                if(droite){

                    type = Type.D;
                }
                if(bas){

                    type = Type.B;
                }
                if(gauche){

                    type = Type.G;
                }

                break;

            case 2:
                if(haut && bas){

                    type = Type.HB;
                }
                if(gauche && droite){

                    type = Type.GD;
                }
                if(haut && droite){

                    type = Type.HD;
                }
                if(droite && bas){

                    type = Type.DB;
                }
                if(bas && gauche){

                    type = Type.BG;
                }
                if(haut && gauche){

                    type = Type.HG;
                }

                break;

            case 3:
                if(!haut){

                    type = Type.DBG;
                }
                if(!droite){

                    type = Type.HBG;
                }
                if(!bas){

                    type = Type.HDG;
                }
                if(!gauche){

                    type = Type.HDB;
                }

                break;

            case 4:
                type = Type.HDBG;
                break;
        }

        return cpt;
    }
}
