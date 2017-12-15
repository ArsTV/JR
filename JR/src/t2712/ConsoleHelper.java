package t2712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import t2712.kitchen.Dish;

public class ConsoleHelper {
    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        reader.close();
        return line;
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        writeMessage(Dish.allDishesToString());

        writeMessage("Type what you choosed from the dishes(one line is one name of a dish, type exit to finish the order):");

        List<Dish> dishes = new ArrayList<>();

        for(;;){
            String lineOfChoosedDishes = readString();
            if (lineOfChoosedDishes.equals("exit")){
                break;
            }

            switch (lineOfChoosedDishes){
                case "Fish":
                    dishes.add(Dish.Fish);
                    break;
                case "Steak":
                    dishes.add(Dish.Steak);
                    break;
                case "Soup":
                    dishes.add(Dish.Soup);
                    break;
                case "Juice":
                    dishes.add(Dish.Juice);
                    break;
                case "Water":
                    dishes.add(Dish.Water);
                    break;
                default:
                    writeMessage("Sorry, we don't have this dish. Please choose another one.");
            }
        }

        return dishes;
    }

}

