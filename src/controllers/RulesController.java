/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui_panels.RulesPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 *
 * @author Lee5
 */
public class RulesController extends Observable implements ActionListener{
    private final RulesPanel rulesView;
    private String modelSelection;

    public RulesController(RulesPanel rulesView){
        this.rulesView = rulesView;
        this.modelSelection = "";
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rulesView.getButton("back")){
            System.out.println("back button pressed");
            modelSelection = "main menu";
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    public String getModelSelection(){
        return this.modelSelection;
    }
    
}
