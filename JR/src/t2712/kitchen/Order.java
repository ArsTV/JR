package t2712.kitchen;

import java.io.IOException;
import java.util.List;

import t2712.ConsoleHelper;
import t2712.Tablet;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;


    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public int getTotalCookingTime() {
        int sumTime = 0;
        for (Dish d : dishes) {
            sumTime += d.getDuration();
        }

        return sumTime;
    }

    @Override
    public String toString() {
        return dishes.isEmpty() ? "" : "Your order: " + dishes + " of " + tablet;
    }
}
