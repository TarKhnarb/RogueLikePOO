package Plateau.Salles;

import Outils.Position;
import Plateau.Direction;
import Plateau.Jeu;
import Plateau.Salles.Cases.EtatSerrure;

import java.io.FileNotFoundException;

public class Etage{

        /*************
         * Variables *
         *************/
    private Jeu jeu;
    private int taille;
    private Salle[][] etage;
    private int seed;
    private final int minSalles;
    private final int maxSalles;

        /****************
         * Constructeur *
         ****************/
    public Etage(Jeu jeu, int taille, int minSalles, int maxSalles) throws Exception{

        this.jeu = jeu;
        this.taille = taille;
        this.minSalles = minSalles;
        this.maxSalles = maxSalles;

        if(maxSalles >= taille*taille){

            throw new Exception("Etage::Etage - Le nomre maximum de salles est supérieur à la capacité maximale de salles");
        }
        if(taille % 2 == 0){

            throw new Exception("La taille de l'étage doit être impaire");
        }
    }

        /*****************
         * GenerateEtage *
         *****************/
    public void genererEtage() throws Exception{

        setSeed();
        int nombreSalles = (int)(Math.random() * (maxSalles - minSalles + 1)) + minSalles;
        int compteurSalles = 2; // Salle de départ et fin déja comptées
        int entropy = 2;        // ATTENTION mini = 1
        int milieu = (taille - 1)/2;

        while(nombreSalles != compteurSalles){

            reset(); // Reset l'etage (nettoie le tableau et genere le depart au milieu du tableau)
            compteurSalles = 0; // Au cas où on recommence

                // Génère les salles de l'étage
            for(int k= 1; k <= milieu; ++k){ // Chaque 'cercles' autour du point central

                for(int i = milieu - k + 1; i <= milieu + k; ++i){ // Première ligne, en haut à l'horizontal, sauf case tout en haut à gauche (car rien encore à côté)

                    if(((i > 0) && (etage[i - 1][milieu - k] != null)) || (etage[i][milieu - k + 1] != null)){ // Test sur les cases à gauche et en dessous

                        if((compteurSalles < nombreSalles - 1) && ((int)(Math.random()*entropy) == (entropy - 1))){ // Place une salle seulement si le random est différent de 0

                            etage[i][milieu - k] = new Salle(Salle.Type.None, Jeu.SIZE,  jeu);
                            ++compteurSalles;
                        }
                    }
                }

                if(etage[milieu - k + 1][milieu - k] != null){ // Traitement de la case tout en haut à gauche

                    if((compteurSalles < nombreSalles - 1) && ((int)(Math.random()*entropy) == (entropy - 1))){ // Place une salle seulement si le random est différent de 0

                        etage[milieu - k][milieu - k] = new Salle(Salle.Type.None, Jeu.SIZE,  jeu);
                        ++compteurSalles;
                    }
                }

                for(int j = milieu - k + 1; j <= milieu + k - 1; ++j){ // Deuxième ligne, à droite à la verticale

                    if(((j > 0) && (etage[milieu + k][j - 1] != null)) || (etage[milieu + k - 1][j] != null)){

                        if((compteurSalles < nombreSalles - 1) && ((int)(Math.random()*entropy) == (entropy - 1))){ // Place une salle seulement si le random est différent de 0

                            etage[milieu + k][j] = new Salle(Salle.Type.None, Jeu.SIZE,  jeu);
                            ++compteurSalles;
                        }
                    }
                }

                for(int j = milieu - k + 1; j <= milieu + k - 1; ++j){ // Troisième ligne, à gauche à la verticale

                    if(((j > 0) && (etage[milieu - k][j - 1] != null)) || (etage[milieu - k + 1][j] != null)){

                        if((compteurSalles < nombreSalles - 1) && ((int)(Math.random()*entropy) == (entropy - 1))){ // Place une salle seulement si le random est différent de 0

                            etage[milieu - k][j] = new Salle(Salle.Type.None, Jeu.SIZE,  jeu);
                            ++compteurSalles;
                        }
                    }
                }

                for(int i = milieu - k; i <= milieu + k; ++i){ // Dernière ligne, en bas à l'horizontal

                    if(((i + 1 < taille) && (etage[i + 1][milieu + k] != null)) || (etage[i][milieu + k - 1] != null)){

                        if((compteurSalles < nombreSalles - 1) && ((int)(Math.random()*entropy) == (entropy - 1))){ // Place une salle seulement si le random est différent de 0

                            etage[i][milieu + k] = new Salle(Salle.Type.None, Jeu.SIZE,  jeu);
                            ++compteurSalles;
                        }
                    }
                }

                    // Salle de fin
                if(compteurSalles == nombreSalles - 1){

                    for(int i = milieu - k + 1; i <= milieu + k; ++i){

                        if((etage[i][milieu - k] == null) && checkSalleAutour(i, milieu - k)){

                            if((int)(Math.random()*entropy) == (entropy - 1)){

                                etage[i][milieu - k] = new Salle(Salle.Type.Fin, Jeu.SIZE,  jeu);
                                ++compteurSalles;
                            }
                        }
                    }

                    if((etage[milieu - k][milieu - k] == null) && checkSalleAutour(milieu - k, milieu - k)){

                        if((int)(Math.random()*entropy) == (entropy - 1)){

                            etage[milieu - k][milieu - k] = new Salle(Salle.Type.Fin, Jeu.SIZE,  jeu);
                            ++compteurSalles;
                        }
                    }

                    for(int j = milieu - k + 1; j <= milieu + k - 1; ++j){

                        if((etage[milieu + k][j] == null) && checkSalleAutour(milieu + k, j)){

                            if((int)(Math.random()*entropy) == (entropy - 1)){

                                etage[milieu + k][j] = new Salle(Salle.Type.Fin, Jeu.SIZE,  jeu);
                                ++compteurSalles;
                            }
                        }
                    }

                    for(int j = milieu - k + 1; j <= milieu + k - 1; ++j){

                        if((etage[milieu - k][j] == null) && checkSalleAutour(milieu - k, j)){

                            if((int)(Math.random()*entropy) == (entropy - 1)){

                                etage[milieu - k][j] = new Salle(Salle.Type.Fin, Jeu.SIZE,  jeu);
                                ++compteurSalles;
                            }
                        }
                    }

                    for(int i = milieu - k; i <= milieu + k; ++i){

                        if((etage[i][milieu + k] == null) && checkSalleAutour(i, milieu + k)){

                            if((int)(Math.random()*entropy) == (entropy - 1)){

                                etage[i][milieu + k] = new Salle(Salle.Type.Fin, Jeu.SIZE,  jeu);
                                ++compteurSalles;
                            }
                        }
                    }
                }
            }
        }

        placerPortes();
        affecterTypeSalles();
        placerCases();
    }

        /***************
         * PlacerCases *
         ***************/
    private void placerCases() throws FileNotFoundException{

        for(int y = 0; y < taille; ++y){

            for(int x = 0; x < taille; ++x){

                if(etage[x][y] != null){

                    etage[x][y].placerCases();
                }
            }
        }
    }

        /************
         * GetSalle *
         ************/
    public Salle getSalle(Position position) throws Exception{

        if(etage[position.getX()][position.getY()] != null){

            return etage[position.getX()][position.getY()];
        }
        else{

            throw new Exception("Etage::getSalle - La salle souhaité n'existe pas");
        }
    }

        /***********
         * GetSeed *
         ***********/
    public int getSeed(){

        return seed;
    }

        /***********
         * SetSeed *
         ***********/
    private void setSeed(){

        seed = (int) (Math.random() * Integer.MAX_VALUE);
    }

        /*********
         * Reset *
         *********/
    private void reset(){

        etage = new Salle[taille][taille];

        int mid = (taille - 1)/2;

        etage[mid][mid] = new Salle(Salle.Type.Debut, Jeu.SIZE, jeu);
    }

        /********************
         * CheckSalleAutour *
         ********************/
    private boolean checkSalleAutour(int x, int y){ // regarde si il y a qu'une seule salle autour d'un case

        int count = 0;

        if(x > 0){

            if(etage[x - 1][y] != null){ // Salle de Gauche

                ++count;
            }
        }

        if(y > 0){

            if(etage[x][y - 1] != null){ // Salle en Haut

                ++count;
            }
        }

        if(x + 1 < taille){

            if(etage[x + 1][y] != null){ // Salle de Droite
                ++count;
            }
        }

        if(y + 1 < taille){

            if(etage[x][y + 1] != null){ // Salle en Bas

                ++count;
            }
        }

        return (count == 1);
    }

        /****************
         * PlacerPortes *
         ****************/
    private void placerPortes(){

        for(int y = 0; y < taille; ++y){

            for(int x = 0; x < taille; ++x){

                if(etage[x][y] != null){ // La salle à été placée

                    if((y > 0) && (etage[x][y - 1] != null)){   // Salle en Haut

                        etage[x][y].ajouterPorte(Direction.Haut, EtatSerrure.Ferme);
                    }

                    if((x + 1 < taille) && (etage[x + 1][y] != null)){ // Salle à Droite

                        etage[x][y].ajouterPorte(Direction.Droite, EtatSerrure.Ferme);
                    }

                    if((y + 1 < taille) && (etage[x][y + 1] != null)){ // Salle en Bas

                        etage[x][y].ajouterPorte(Direction.Bas, EtatSerrure.Ferme);
                    }

                    if((x > 0) && (etage[x - 1][y] != null)){ // Salle à Gauche

                        etage[x][y].ajouterPorte(Direction.Gauche, EtatSerrure.Ferme);
                    }
                }
            }
        }
    }

        /**********************
         * AffecterTypeSalles *
         **********************/
    private void affecterTypeSalles() throws Exception{

        for(int y = 0; y < taille; ++y){

            for(int x = 0; x < taille; ++x){

                if(etage[x][y] != null){

                    if(etage[x][y].getType() == Salle.Type.None){

                        etage[x][y].affecterType(seed);
                    }
                }
            }
        }
    }

        /*************
         * ToString *
         *************/
    @Override
    public String toString(){

        String retour = "";

        for(int y = 0; y < taille; ++y){

            for(int x = 0; x < taille; ++x){

                if(etage[x][y] == null){

                    retour += "-";
                }
                else{

                    switch(etage[x][y].getType()){

                        case Debut:
                            retour += "A";
                            break;

                        case Fin:
                            retour += "Z";
                            break;

                        case H:
                            retour += "B";
                            break;

                        case D:
                            retour += "C";
                            break;

                        case B:
                            retour += "D";
                            break;

                        case G:
                            retour += "E";
                            break;

                        case HB:
                            retour += "F";
                            break;

                        case GD:
                            retour += "G";
                            break;

                        case HD:
                            retour += "H";
                            break;

                        case DB:
                            retour += "I";
                            break;

                        case BG:
                            retour += "J";
                            break;

                        case HG:
                            retour += "K";
                            break;

                        case HDB:
                            retour += "L";
                            break;

                        case DBG:
                            retour += "M";
                            break;

                        case HBG:
                            retour += "N";
                            break;

                        case HDG:
                            retour += "O";
                            break;

                        case HDBG:
                            retour += "P";
                            break;

                        case None:
                            retour += "/";
                            break;

                        default:
                            retour += "!";
                            break;
                    }
                }
            }

            retour += "\n";
        }

        return retour;
    }
}
