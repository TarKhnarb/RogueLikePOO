package Outils;

import Plateau.Direction;

public class Position{

        /*************
         * Variables *
         *************/
    private Coordonnee coordonnee;
    private final Coordonnee zoneMin;
    private final Coordonnee zoneMax;

        /****************
         * Constructeur *
         ****************/
    public Position(Coordonnee coordonnee, Coordonnee zoneMin, Coordonnee zoneMax){

        this.coordonnee = coordonnee;
        this.zoneMin = zoneMin;
        this.zoneMax = zoneMax;
    }

        /***************
         * SetPosition *
         ***************/
    public void setPosition(Coordonnee coordonnee) throws Exception{

        if((zoneMin.x <= coordonnee.x) && (coordonnee.x <= zoneMax.x) && (zoneMin.y <= coordonnee.y) && (coordonnee.y <= zoneMax.y)){

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

        /**************
         * GetZoneMin *
         **************/
    public Coordonnee getZoneMin(){

        return zoneMin;
    }

        /**************
         * GetZoneMax *
         **************/
    public Coordonnee getZoneMax(){

        return zoneMax;
    }

        /********
         * Move *
         ********/
    public boolean move(Direction direction){

        Coordonnee coord = coordonnee.getCoord(direction);

        if((zoneMin.x <= coord.x) && (coord.x <= zoneMax.x) && (zoneMin.y <= coord.y) && (coord.y <= zoneMax.y)){

            this.coordonnee.setCoord(coord);
            return true;
        }

        return false;
    }

        /*******************
         * CentrerDansZone *
         *******************/
    public void centrerDansZone(){

        if((zoneMax.x - zoneMin.x)%2 == 0){

            if((zoneMax.y - zoneMin.y)%2 == 0){

                coordonnee.x = (zoneMax.x - zoneMin.y)/2;
                coordonnee.y = (zoneMax.y - zoneMin.y)/2;
            }
            else{

                coordonnee.x = (zoneMax.x - zoneMin.x)/2;
                coordonnee.y = (zoneMax.y - zoneMin.y - 1)/2;
            }
        }
        else{

            if((zoneMax.y - zoneMin.y)%2 == 0){

                coordonnee.x = (zoneMax.x - zoneMin.x - 1)/2;
                coordonnee.y = (zoneMax.y - zoneMin.y)/2;
            }
            else{

                coordonnee.x = (zoneMax.x - zoneMin.x - 1)/2;
                coordonnee.y = (zoneMax.y - zoneMin.y - 1)/2;
            }
        }
    }

        /************
         * GetCoord *
         ************/
    public Coordonnee getCoord(Direction direction){

        Coordonnee coord = coordonnee.getCoord(direction);
        if((zoneMin.x <= coord.x) && (coord.x <= zoneMax.x) && (zoneMin.y <= coord.y) && (coord.y <= zoneMax.y)){

            return coord;
        }

        return null;
    }

        /************
         * GetCoord *
         ************/
    public Coordonnee getCoord(){

        return coordonnee;
    }
}
