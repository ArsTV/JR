package t2712;


import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import t2712.ad.AdvertisementManager;
import t2712.kitchen.Order;

/**
 * Created by DELL on 12/13/2017.
 */
public class Tablet extends Observable {
    final int number;
    AdvertisementManager advertisementManager;

    public static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {

        this.number = number;
    }

    public Order createOrder(){
        Order order = null;
        try
        {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());

            if(!order.isEmpty()){
                setChanged();
                notifyObservers(order);
                advertisementManager = new AdvertisementManager(order.getTotalCookingTime()*60);
                advertisementManager.processVideos();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
        return order;
    }

    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }

}
