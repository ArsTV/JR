package t2712;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import t2712.kitchen.Order;

/**
 * Created by DELL on 12/13/2017.
 */
public class Tablet {
    final int number;
    public static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {

        this.number = number;
    }

    public void createOrder(){
        try{
            Order order = new Order(this);
        } catch (IOException e){
            logger.log(Level.SEVERE, "Console is unavailable.");
        }

    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
