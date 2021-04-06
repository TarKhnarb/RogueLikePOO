package Outils;

import Plateau.Direction;

public class Position{

        /*************
         * Variables *
         *************/
    private Coordonnee coordonnee;
    private final Taille zoneMin;
    private final Taille zoneMax;

        /****************
         * Constructeur *
         ****************/
    public Position(Coordonnee coordonnee, Taille zoneMin, Taille zoneMax){

        this.coordonnee = coordonnee;
        this.zoneMin = zoneMin;
        this.zoneMax = zoneMax;
    }

        /***************
         * SetPosition *
         ***************/
    public void setPosition(Coordonnee coordonnee) throws Exception{

        if((zoneMin.getX() <= coordonnee.x) && (coordonnee.x <= zoneMax.getX()) && (zoneMin.getY() <= coordonnee.y) && (coordonnee.y <= zoneMax.getY())){

            this.coordonnee = coordonnee;
        }
        else{

            throw new Exception("Position::setPosition - La position souhaitée est en dehors de la zone");
        }
    }

        /********
         * GetX *
         ********/
    public int getX() {

        return coordonnee.x;
    }

        /********
         * GetY *
         ********/
    public int getY() {

        return coordonnee.y;
    }

    public Coordonnee getCoord(){

        return coordonnee;
    }

        /**************
         * GetZoneMin *
         **************/
    public Taille getZoneMin(){

        return zoneMin;
    }

        /**************
         * GetZoneMax *
         **************/
    public Taille getZoneMax(){

        return zoneMax;
    }

        /********
         * Move *
         ********/
    public boolean move(Direction direction){

        Coordonnee coord = coordonnee.getCoord(direction);

        if((zoneMin.getX() <= coord.x) && (coord.x <= zoneMax.getX()) && (zoneMin.getY() <= coord.y) && (coord.y <= zoneMax.getY())){

            this.coordonnee.setCoord(coord);
            return true;
        }

        return false;
    }

    public void centrerDansZone(){

        if((zoneMax.getX() - zoneMin.getX())%2 == 0){

            if((zoneMax.getY() - zoneMin.getY())%2 == 0){

                coordonnee.x = (zoneMax.getX() - zoneMin.getX())/2;
                coordonnee.y = (zoneMax.getY() - zoneMin.getY())/2;
            }
            else{

                coordonnee.x = (zoneMax.getX() - zoneMin.getX())/2;
                coordonnee.y = (zoneMax.getY() - zoneMin.getY() - 1)/2;
            }
        }
        else{

            if((zoneMax.getY() - zoneMin.getY())%2 == 0){

                coordonnee.x = (zoneMax.getX() - zoneMin.getX() - 1)/2;
                coordonnee.y = (zoneMax.getY() - zoneMin.getY())/2;
            }
            else{

                coordonnee.x = (zoneMax.getX() - zoneMin.getX() - 1)/2;
                coordonnee.y = (zoneMax.getY() - zoneMin.getY() - 1)/2;
            }
        }
    }

    public Coordonnee getCoord(Direction direction){

        Coordonnee coord = coordonnee.getCoord(direction);
        if((zoneMin.getX() <= coord.x) && (coord.x <= zoneMax.getX()) && (zoneMin.getY() <= coord.y) && (coord.y <= zoneMax.getY())){

            return coord;
        }

        return null;
    }
}
