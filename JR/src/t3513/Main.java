package t3513;

import javax.swing.*;

/**
 * Created by DELL on 1/12/2018.
 */
public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        JFrame gameFrame = new JFrame();

        gameFrame.setTitle("2048");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setSize(450, 500);
        gameFrame.setResizable(false);

        gameFrame.add(controller.getView());


        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

    }
}