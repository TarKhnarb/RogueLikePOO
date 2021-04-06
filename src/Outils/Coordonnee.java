package Outils;

import Plateau.Direction;

public class Coordonnee{

    public int x;
    public int y;

    public Coordonnee(int x, int y){

        this.x = x;
        this.y = y;
    }

    public Coordonnee(Coordonnee coordonnee){

        this.x = coordonnee.x;
        this.y = coordonnee.y;
    }

    public void setCoord(int x, int y){

        this.x = x;
        this.y = y;
    }

    public void setCoord(Coordonnee coordonnee){

        this.x = coordonnee.x;
        this.y = coordonnee.y;
    }

    public Coordonnee getCoord(Direction direction){
        
        switch(direction){

            case Haut:
                return new Coordonnee(this.x, (this.y - 1));

            case Droite:
                return new Coordonnee((this.x + 1), this.y);

            case Bas:
                return new Coordonnee(this.x, (this.y + 1));

            case Gauche:
                return new Coordonnee((this.x - 1), this.y);

            case All:
            default:
                return this;
        }
    }
}
