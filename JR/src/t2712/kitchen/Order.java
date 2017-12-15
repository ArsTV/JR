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

    @Override
    public String toString() {
        StringBuilder order = new StringBuilder();
        if(dishes.isEmpty()){
            order.append("");
        } else {
            for (int i = 0; i < dishes.size(); i++) {
                order.append(dishes.get(i).toString());
                if (i != dishes.size()-1){
                    order.append(", ");
                }
            }
            return "Your order: [" + order.toString() + "] of " + tablet;
        }
        return super.toString();
    }
}
