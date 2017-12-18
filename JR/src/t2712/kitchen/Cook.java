package t2712.kitchen;

import java.util.Observable;
import java.util.Observer;

import t2712.ConsoleHelper;

public class Cook extends Observable implements Observer{
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable obj, Object arg) {
        //Order order = (Order) arg;
        ConsoleHelper.writeMessage("Start cooking - " + arg);
        setChanged();
        notifyObservers(arg);
    }
}
