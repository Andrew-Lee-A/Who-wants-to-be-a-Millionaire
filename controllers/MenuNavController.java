package controllers;

import gui_panels.HighscorePanel;
import gui_panels.MainMenuPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * This class represents both the model, and the controller as no model exists
 * for button navigation (based on mouse clicks which in turn is an action
 * listener)
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class MenuNavController extends Observable implements ActionListener {

    private final MainMenuPanel menuView;
    private String modelSelection;
    
    public MenuNavController(MainMenuPanel menuView) {
        this.menuView = menuView;
        this.modelSelection = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuView.getButton("exit")) {
            System.exit(0);
        } else if (e.getSource() == menuView.getButton("play")) {
            menuView.getTimer().startTimer();
            modelSelection = "play";
            this.setChanged();
            this.notifyObservers();
        } else if (e.getSource() == menuView.getButton("highscores")){
            menuView.getGameState().updateRecords();
            HighscorePanel.updateData();
            modelSelection = "highscores";
            this.setChanged();
            this.notifyObservers();
        } else if (e.getSource() == menuView.getButton("rules")){
            modelSelection = "rules";
            this.setChanged();
            this.notifyObservers();
        }
 
    }
    
    public String getModelSelection() {
        return this.modelSelection;
    }
}
