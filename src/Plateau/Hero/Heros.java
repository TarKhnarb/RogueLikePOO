/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plateau.Hero;

import Outils.Coordonnee;
import Outils.Position;
import Plateau.Direction;
import Plateau.Jeu;
import Plateau.Salles.Cases.TypeCase;

/**
 * Héros du jeu
 */
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

                throw new Exception("Changement de salle impossible,  la direction n'a pas été affectée correctement");
        }
    }

        /***************
         * Traversable *
         ***************/
    private boolean traversable(Direction dir) throws Exception{

        switch(dir){

            case Haut:
                if(0 <= (position.getY() - 1)){

                    return jeu.getEntite(position.getX(), (position.getY() - 1)).traversable();
                }

            case Droite:
                if((position.getX() + 1) < jeu.SIZE.getX()){

                    return jeu.getEntite((position.getX() + 1), position.getY()).traversable();
                }

            case Bas:
                if((position.getY() + 1) < jeu.SIZE.getY()){

                    return jeu.getEntite(position.getX(), (position.getY() + 1)).traversable();
                }

            case Gauche:
                if(0 <= (position.getX() - 1)){

                    return jeu.getEntite((position.getX() - 1), position.getY()).traversable();
                }

            case All:
            default:
                return false;
        }
    }

        /*****************
         * Lancercapsule *
         *****************/
    public void lancercapsule() throws Exception{

        Coordonnee coord = position.getCoord(direction);
        if(coord == null) {

            throw new Exception("Lancement impossible, la direction n'a pas été affectée correctement");
        }

        if( (this.jeu.getEntite(coord.x, coord.y).getTypeCase()).equals(TypeCase.Feu) ){

            if( inventaire.getInventaire(Inventaire.Element.Capsule) != 0){

                //System.out.println(this.jeu.getEntite(coord.x, coord.y).getTypeCase() + " " + direction.name());
                inventaire.enleverNElement(Inventaire.Element.Capsule, 1);
                this.jeu.getEntite(coord.x, coord.y).updateCase();
            }
        }
    }
}
