package t2515;

import java.util.ArrayList;

/**
 * Created by DELL on 1/22/2018.
 */
public class Space {
    private int width, height;
    private SpaceShip ship;
    private ArrayList<Ufo> ufos = new ArrayList<Ufo>();
    private ArrayList<Rocket> rockets = new ArrayList<Rocket>();
    private ArrayList<Bomb> bombs = new ArrayList<Bomb>();

    public Space(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public ArrayList<Ufo> getUfos() {
        return ufos;
    }

    public ArrayList<Rocket> getRockets() {
        return rockets;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public static void main(String[] args) {

    }

    public void run(){

    }

    public void draw(){}

    public void sleep(int ms){}
}
