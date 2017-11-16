package t3209.listeners;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import t3209.View;

/**
 * Created by DELL on 11/15/2017.
 */
public class TabbedPaneChangeListener implements ChangeListener {
    private View view ;

    public TabbedPaneChangeListener(View view) {
        this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        view.selectedTabChanged();
    }

}
