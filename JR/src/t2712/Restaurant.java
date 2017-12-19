package t2712;

import t2712.kitchen.Cook;
import t2712.kitchen.Waiter;

public class Restaurant  {
    public static void main(String[] args) {
    	 Cook cook = new Cook("Michelin");
    	 Waiter waiter = new Waiter();
         Tablet tablet = new Tablet(5);
         tablet.addObserver(cook);
         cook.addObserver(waiter);
         tablet.createOrder();
    }
}
