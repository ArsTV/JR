package t3209.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import t3209.View;

/**
 * Created by DELL on 11/14/2017.
 */
public class FrameListener extends WindowAdapter {
    private View view;

    public FrameListener(View view) {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        view.exit();
    }
}
