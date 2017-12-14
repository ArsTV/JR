package t2712.kitchen;

public enum Dish {
    Fish,
    Steak,
    Soup,
    Juice,
    Water;

    public static String allDishesToString(){
        return Dish.Fish.toString() + ", " +
                Dish.Steak.toString() + ", " +
                Dish.Soup.toString() + ", " +
                Dish.Juice.toString() + ", " +
                Dish.Water.toString();
    }
}
