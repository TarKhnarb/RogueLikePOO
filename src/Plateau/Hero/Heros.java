package Plateau.Hero;

import Outils.Coordonnee;
import Outils.Position;
import Plateau.Direction;
import Plateau.Jeu;
import Plateau.Salles.Cases.EntiteStatique;
import Plateau.Salles.Cases.TypeCase;

public class Heros{

        /*************
         * Variables *
         *************/
    private Position position;
    private Jeu jeu;
    private Inventaire inventaire;
    private Direction direction;

        /****************
         * Constructeur *
         ****************/
    public Heros(Jeu jeu, Position position){

        this.position = position;
        this.jeu = jeu;

        this.inventaire = new Inventaire();
        this.direction = Direction.Bas;

        try{

            this.inventaire.ajouterNElement(Inventaire.Element.Cle, Jeu.NBCLE);
            this.inventaire.ajouterNElement(Inventaire.Element.Capsule, Jeu.NBCAPSULES);
        }
        catch(Exception e){

            e.printStackTrace();
        }
    }

        /********
         * GetX *
         ********/
    public int getX(){

        return position.getX();
    }

        /********
         * GetY *
         ********/
    public int getY(){

        return position.getY();
    }

        /***************
         * GetPosition *
         ***************/
    public Position getPosition(){

        return position;
    }

        /*****************
         * GetInventaire *
         *****************/
    public Inventaire getInventaire(){

        return inventaire;
    }

        /****************
         * GetDirection *
         ****************/
    public Direction getDirection(){

        return direction;
    }

        /********
         * Move *
         ********/
    public void bouger(Direction direction) throws Exception{

        this.direction = direction;
        if(traversable(this.direction)){

            position.move(this.direction);
            jeu.getEntite(position.getCoord()).updateCase();
        }
    }

        /********************
         * CentrerDansSalle *
         ********************/
    public void centrerDansSalle(){

        position.centrerDansZone();
    }

        /****************
         * ChangerSalle *
         ****************/
    public void changerSalle(Direction direction) throws Exception{

        switch(direction){

            case Haut:

                position.setPosition(new Coordonnee((Jeu.SIZE.getX() - 1)/2, Jeu.SIZE.getY() - 1)); // Bas
                break;

            case Droite:

                position.setPosition(new Coordonnee(0, (Jeu.SIZE.getY() - 1)/2)); //Gauche
                break;

            case Bas:

                position.setPosition(new Coordonnee((Jeu.SIZE.getX() - 1)/2, 0)); //Haut
                break;

            case Gauche:

                position.setPosition(new Coordonnee(Jeu.SIZE.getX() - 1, (Jeu.SIZE.getY() - 1)/2));
                break;

            case All:
            default:

                throw new Exception("Heros::lancerCapsule - Changement de salle impossible,  la direction n'a pas été affectée correctement");
        }

        inventaire.setInventaire(Inventaire.Element.Capsule, Jeu.NBCAPSULES);
    }

        /***************
         * Traversable *
         ***************/
    private boolean traversable(Direction dir) throws Exception{

        Coordonnee coord = position.getCoord(dir);
        if(coord == null) {

            throw new Exception("Heros::traversable - La direction n'a pas été affectée correctement");
        }

        return jeu.getEntite(coord).traversable();
    }

        /*****************
         * Lancercapsule *
         *****************/
    public void lancerCapsule() throws Exception{

        Coordonnee coord = position.getCoord(direction);
        if(coord == null) {

            throw new Exception("Heros::lancerCapsule - Lancement impossible, la direction n'a pas été affectée correctement");
        }

        if(this.jeu.getEntite(coord).getTypeCase().equals(TypeCase.Feu)){

            if( inventaire.getInventaire(Inventaire.Element.Capsule) != 0){

                inventaire.enleverNElement(Inventaire.Element.Capsule, 1);
                this.jeu.getEntite(coord).updateCase();
            }
        }
    }

        /**********
         * Sauter *
         **********/
    public void sauter() throws Exception{

        Coordonnee coord = position.getCoord(direction);
        if(coord == null) {

            throw new Exception("Heros::sauter - Lancement impossible, la direction n'a pas été affectée correctement");
        }

        EntiteStatique entite = this.jeu.getEntite(coord);
        if((entite.getTypeCase() == TypeCase.Vide)){

            Coordonnee coord2 = new Coordonnee(new Position(coord, getPosition().getZoneMin(), getPosition().getZoneMax()).getCoord(direction)); // deux cases en avant
            if(coord2 == null) {

                throw new Exception("Heros::sauter - Lancement impossible, la direction n'a pas été affectée correctement");
            }

            EntiteStatique entite2 = this.jeu.getEntite(coord2);
            if((entite2.getTypeCase() == TypeCase.Normale) || (entite2.getTypeCase() == TypeCase.Cle) || (entite2.getTypeCase() == TypeCase.Unique)){

                position.setPosition(coord2);
                jeu.getEntite(position.getCoord()).updateCase();
            }
        }
    }
}
