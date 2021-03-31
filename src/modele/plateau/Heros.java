/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

/**
 * Héros du jeu
 */
public class Heros{

    private int x;
    private int y;

    private Jeu jeu;
    private Inventaire inventaire;

    public Heros(Jeu jeu, int x, int y){

        this.jeu = jeu;
        this.x = x;
        this.y = y;

        inventaire = new Inventaire();
    }

    public int getX(){

        return x;
    }

    public int getY(){

        return y;
    }

    public Inventaire getInventaire(){

        return inventaire;
    }

    public void droite(){

        if(traversable(x+1, y)){

            ++x;
        }
    }

    public void gauche(){

        if(traversable(x-1, y)){

            --x;
        }
    }

    public void bas(){

        if(traversable(x, y+1)){

            ++y;
        }
    }

    public void haut(){

        if(traversable(x, y-1)){

            --y;
        }
    }

    private boolean traversable(int x, int y){

        if((x > 0) && (x < jeu.SIZE_X) && (y > 0) && (y < jeu.SIZE_Y)){

            return jeu.getEntite(x, y).traversable();
        }
        else{

            return false;
        }
    }
}
