/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * This class is a data structure to model a panel, and card
 * layout manager, it is used to pass both pieces of information
 * as they are required together to 'switch' between panels.
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class CurrentPanel {
    private JPanel selectedPanel;
    private CardLayout cardLayout;
    
    // note this assumes that the name identifier for the main menu
    // panel will be "main menu" and the play game panel will be 
    // "play game"
    public CurrentPanel(JPanel selectedPanel, CardLayout cardLayout) {
        this.selectedPanel = selectedPanel;
        this.cardLayout = cardLayout;
    }
    
    public void goToMainMenu() {
        cardLayout.show(selectedPanel, "main menu");
    }
    
    public void goToPlayGame() {
        cardLayout.show(selectedPanel, "play game");
    }
}
