package Outils;

import Plateau.Direction;

public class Position{

        /*************
         * Variables *
         *************/
    private int x;
    private int y;
    private final Taille zoneMin;
    private final Taille zoneMax;

        /****************
         * Constructeur *
         ****************/
    public Position(int x, int y, Taille zoneMin, Taille zoneMax){

        this.x = x;
        this.y = y;
        this.zoneMin = zoneMin;
        this.zoneMax = zoneMax;
    }

        /***************
         * SetPosition *
         ***************/
    public void setPosition(int x, int y) throws Exception{

        if((zoneMin.getX() <= x) && (x <= zoneMax.getX()) && (zoneMin.getY() <= y) && (y <= zoneMax.getY())){

            this.x = x;
            this.y = y;
        }
        else{

            throw new Exception("Position::setPosition - La position souhaitée est en dehors de la zone");
        }
    }

        /********
         * GetX *
         ********/
    public int getX() {

        return x;
    }

        /********
         * GetY *
         ********/
    public int getY() {

        return y;
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

        switch(direction){

            case Haut:
                if((y - 1) >= zoneMin.getY()){

                    --y;
                    return true;
                }
                else{

                    return false;
                }

            case Droite:
                if((x + 1) <= zoneMax.getX()){

                    ++x;
                    return true;
                }
                else{

                    return false;
                }

            case Bas:
                if((y + 1) <= zoneMax.getY()){

                    ++y;
                    return true;
                }
                else{

                    return false;
                }

            case Gauche:
                if((x - 1) >= zoneMin.getX()){

                    --x;
                    return true;
                }
                else{

                    return false;
                }

            case All:
            default:
                return false;
        }
    }

    public void centrerDansZone(){

        if((zoneMax.getX() - zoneMin.getX())%2 == 0){

            if((zoneMax.getY() - zoneMin.getY())%2 == 0){

                x = (zoneMax.getX() - zoneMin.getX())/2;
                y = (zoneMax.getY() - zoneMin.getY())/2;
            }
            else{

                x = (zoneMax.getX() - zoneMin.getX())/2;
                y = (zoneMax.getY() - zoneMin.getY() - 1)/2;
            }
        }
        else{

            if((zoneMax.getY() - zoneMin.getY())%2 == 0){

                x = (zoneMax.getX() - zoneMin.getX() - 1)/2;
                y = (zoneMax.getY() - zoneMin.getY())/2;
            }
            else{

                x = (zoneMax.getX() - zoneMin.getX() - 1)/2;
                y = (zoneMax.getY() - zoneMin.getY() - 1)/2;
            }
        }
    }
}
