package t2712.kitchen;

import java.util.Observable;
import java.util.Observer;

import t2712.ConsoleHelper;

/**
 * Created by DELL on 12/18/2017.
 */
public class Waiter implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage(arg + " was cooked by " + o);
    }

}