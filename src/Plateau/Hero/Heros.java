/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plateau.Hero;

import Outils.Position;
import Plateau.Direction;
import Plateau.Jeu;
import Plateau.Salles.Cases.TypeCase;

import static Plateau.Salles.Cases.TypeCase.Feu;

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
                position.setPosition((Jeu.SIZE.getX() - 1)/2, Jeu.SIZE.getY() - 1); // Bas
                break;

            case Droite:
                position.setPosition(0, (Jeu.SIZE.getY() - 1)/2); //Gauche
                break;

            case Bas:
                position.setPosition((Jeu.SIZE.getX() - 1)/2, 0); //Haut
                break;

            case Gauche:
                position.setPosition(Jeu.SIZE.getX() - 1, (Jeu.SIZE.getY() - 1)/2);
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

        System.out.println("test fonction de lancer capsule");

        switch(direction){

            case Haut:
                if(0 <= (position.getY() - 1)){

                    if( (this.jeu.getEntite(position.getX(), position.getY() - 1).getTypeCase()).equals(Feu) ){

                        if(this.jeu.getHeros().getInventaire().getInventaire(Inventaire.Element.Capsule) != 0){

                            this.jeu.getHeros().getInventaire().enleverNElement(Inventaire.Element.Capsule, 1);
                            System.out.println(this.jeu.getEntite(position.getX(), position.getY() - 1).getTypeCase() + " Haut");
                        }
                    }
                }
                break;

            case Droite:
                if((position.getX() + 1) < jeu.SIZE.getX()){

                    if( (this.jeu.getEntite(position.getX()+1, position.getY()).getTypeCase()).equals(Feu) ){

                        if(this.jeu.getHeros().getInventaire().getInventaire(Inventaire.Element.Capsule) != 0){

                            this.jeu.getHeros().getInventaire().enleverNElement(Inventaire.Element.Capsule, 1);
                            System.out.println(this.jeu.getEntite(position.getX() + 1, position.getY()).getTypeCase() + " Droite");
                        }
                    }
                }
                break;

            case Bas:
                if((position.getY() + 1) < jeu.SIZE.getY()){

                    if( (this.jeu.getEntite(position.getX(), position.getY() + 1).getTypeCase()).equals(Feu) ){

                        if(this.jeu.getHeros().getInventaire().getInventaire(Inventaire.Element.Capsule) != 0){

                            this.jeu.getHeros().getInventaire().enleverNElement(Inventaire.Element.Capsule, 1);
                            System.out.println(this.jeu.getEntite(position.getX(), position.getY() + 1).getTypeCase() + " Bas");
                        }
                    }
                }
                break;

            case Gauche:
                if(0 <= (position.getX() - 1)){

                    if( (this.jeu.getEntite(position.getX() - 1, position.getY()).getTypeCase()).equals(Feu) ){

                        if(this.jeu.getHeros().getInventaire().getInventaire(Inventaire.Element.Capsule) != 0){

                            this.jeu.getHeros().getInventaire().enleverNElement(Inventaire.Element.Capsule, 1);
                            System.out.println(this.jeu.getEntite(position.getX() - 1, position.getY()).getTypeCase() + " Gauche");
                        }
                    }
                }
                break;

            case All:
            default:

                throw new Exception("Lancement impossible, la direction n'a pas été affectée correctement");
        }

    }
}
