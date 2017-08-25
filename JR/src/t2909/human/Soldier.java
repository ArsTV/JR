package t2909.human;

public class Soldier extends Human {
    protected boolean isSoldier = true;

    public Soldier(String name, int age) {
        super(name, age);
        //isSoldier = true;
    }

    public void live() {
        if (isSoldier)
            fight();
    }

     public void fight() {
    }
}
