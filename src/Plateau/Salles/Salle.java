package Plateau.Salles;

import Outils.Coordonnee;
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
        HB2,
        GD,
        GD2,
        HD,
        DB,
        BG,
        HG,
        HDB,
        HDB2,
        DBG,
        DBG2,
        HBG,
        HBG2,
        HDG,
        HDG2,
        HDBG,
        None
    }

        /*************
         * Variables *
         *************/
    private Type type;
    private EntiteStatique[][] grille;
    private Porte[] portes;
    private Coordonnee taille;
    private Jeu jeu;

        /****************
         * Constructeur *
         ****************/
    public Salle(Type type, Coordonnee taille, Jeu jeu){

        this.type = type;
        this.taille = taille;
        this.jeu = jeu;

        this.grille = new EntiteStatique[taille.x][taille.y];
        this.portes = new Porte[4];
    }

        /************
         * GetClass *
         ************/
    private EntiteStatique getClass(TypeCase type, Direction direction){

        switch(type){

            case Normale:
                return new CaseNormale(jeu);

            case Vide:
                return new CaseVide(jeu);

            case PorteFerme:
                return new Porte(jeu, direction, EtatSerrure.Ferme);

            case Coffre:
                return new Coffre(jeu, EtatSerrure.Ferme, this.jeu.getPartie().getEtage().getSeed());

            case Cle:
                return new CaseCle(jeu, this.jeu.getPartie().getEtage().getSeed());

            case Unique:
                return new CaseUnique(jeu,false);

            case Feu:
                return new CaseUnique(jeu, true);

            case PorteOuvert:
                return new Porte(jeu, direction, EtatSerrure.Ouvert);

            case PorteEtage:
                return new Porte(jeu, direction, EtatSerrure.Etage);

            case Mur:
            default:
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
        else if(x == taille.x - 1){

            return Direction.Droite;
        }
        else if(y == 0){

            return Direction.Haut;
        }
        else if(y == taille.y - 1){

            return Direction.Bas;
        }
        else{

            return Direction.All;
        }
    }

        /***************
         * PlacePortes *
         ***************/
    private void placePortes(){

        for(int i = 0; i < Direction.All.ordinal(); ++i){

            if(portes[i] != null){

                switch(Direction.values()[i]){

                    case Haut:
                        grille[(jeu.SIZE.x - 1)/2][0] = portes[i];
                        break;

                    case Droite:
                        grille[Jeu.SIZE.x - 1][(Jeu.SIZE.y - 1)/2] = portes[i];
                        break;

                    case Bas:
                        grille[(jeu.SIZE.x - 1)/2][jeu.SIZE.y - 1] = portes[i];
                        break;

                    case Gauche:
                        grille[0][(Jeu.SIZE.y - 1)/2] = portes[i];
                        break;

                    case All:
                    default:
                        break;
                }
            }
        }
    }

        /***************
         * PlacerCases *
         ***************/
    public void placerCases() throws FileNotFoundException{

        Scanner scanner = new Scanner(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Salles/Salle_" + this.type.name() + ".salle")));

        for(int y = 0; y < Jeu.SIZE.y; ++y){

            for(int x = 0; x < Jeu.SIZE.x; ++x){

                if(scanner.hasNextInt()){

                    grille[x][y] = getClass(TypeCase.values()[scanner.nextInt()], getDirectionPorte(x, y));
                }
                else{

                    grille[x][y] = new Mur(jeu);
                }
            }
        }

        if(type.equals(Type.Debut) || type.equals(Type.Fin)){

            placePortes();
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
    public int affecterType(int seed) throws Exception{

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

                    if(seed%(((int) (Math.random() * 3)) + 1) != 1){

                        type = Type.HB2;
                    }
                    else{

                        type = Type.HB;
                    }
                }
                if(gauche && droite){

                    if(seed%(((int) (Math.random() * 2)) + 1) != 1){

                        type = Type.GD;
                    }
                    else{

                        type = Type.GD2;
                    }
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

                    if(seed%(((int) (Math.random() * 4)) + 1) != 1){

                        type = Type.DBG2;
                    }
                    else{

                        type = Type.DBG;
                    }
                }
                if(!droite){

                    if(seed%(((int) (Math.random() * 3)) + 1) != 1){

                        type = Type.HBG2;
                    }
                    else{

                        type = Type.HBG;
                    }
                }
                if(!bas){

                    if(seed%(((int) (Math.random() * 4)) + 1) != 1){

                        type = Type.HDG;
                    }
                    else{

                        type = Type.HDG2;
                    }
                }
                if(!gauche){

                    if(seed%(((int) (Math.random() * 3)) + 1) != 1){

                        type = Type.HDB2;
                    }
                    else{

                        type = Type.HDB;
                    }
                }

                break;

            case 4:
                type = Type.HDBG;
                break;
        }

        return cpt;
    }

        /************
         * ToString *
         ************/
    public String toString(){

        String retour = "";

        for(int y = 0; y < taille.y; ++y){

            for(int x = 0; x < taille.x; ++x){

                if(grille[x][y] != null){

                    retour += grille[x][y].toString();
                }
                else{

                    retour += "!";
                }
                //retour += ((grille[x][y] != null) ? grille[x][y].toString() : "!");
            }

            retour += "\n";
        }

        return retour;
    }
}
