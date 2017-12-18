package t2712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import t2712.kitchen.Dish;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String line = reader.readLine();
        return line;
    }

       
    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        ConsoleHelper.writeMessage("Type what you choosed from the dishes%n(one line is one name of a dish, type exit to finish the order):");
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        while (true) {
            String dishToOrder = readString();
            if (dishToOrder.equalsIgnoreCase("exit")) {
                break;
            }

            if(dishToOrder.isEmpty()){
                writeMessage("Dish is not selected");
                continue;
            }
            boolean found = false;
            for(Dish d : Dish.values())
                if(d.name().equalsIgnoreCase(dishToOrder)) {
                    dishes.add(d);
                    found = true;
                }
            if(!found){
                writeMessage("Sorry, we don't have this dish. Please choose another one");
            }
        }
        return dishes;
    }

}

