package t2712.kitchen;

import java.util.List;

import t2712.Tablet;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;


    public Order(Tablet tablet) {
        this.tablet = tablet;
    }
}