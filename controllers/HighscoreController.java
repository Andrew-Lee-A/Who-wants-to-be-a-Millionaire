/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import game_db.GameDBManager;
import gui_panels.HighscorePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 *
 * @author Lee5
 */
public class HighscoreController extends Observable implements ActionListener{
    private final HighscorePanel highScoreView;
    private String modelSelection;

    public HighscoreController(HighscorePanel highScoreView){
        this.highScoreView = highScoreView;
        this.modelSelection = "";
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == highScoreView.getButton("back")){
            modelSelection = "main menu";           
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    public String getModelSelection(){
        return this.modelSelection;
    }
    
}
