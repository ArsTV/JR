package t2712;

import t2712.kitchen.Cook;

public class Restaurant  {
    public static void main(String[] args) {
    	 Cook cook = new Cook("Michelin");
         Tablet tablet = new Tablet(5);
         tablet.addObserver(cook);
         tablet.createOrder();
    }
}
