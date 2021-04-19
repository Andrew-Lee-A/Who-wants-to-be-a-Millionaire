/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_components;

import gui_styling.OnHoverButton;
import animation.CurrentPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * this class is used to create and initialize the walk away button when a game
 * is being played attaching relevant action listeners
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class WalkAwayButton extends JPanel {

    private final JButton button;
    private final Color defaultBackground = new Color(64, 64, 206);
    private final Color onHoverBackground = new Color(206, 64, 64);

    public WalkAwayButton(String text, Dimension size, CurrentPanel currentPanel) {
        button = new JButton(text);
        button.setSize(size);
        button.setBackground(defaultBackground);
        button.setForeground(new Color(255, 255, 255));
        button.setOpaque(true);
        button.setFocusable(false);
        button.addMouseListener(new OnHoverButton(button, defaultBackground, onHoverBackground));

        super.add(button);
        super.setSize(size);
        super.setLayout(null);
    }
    
    public JButton getWalkAwayButton() {
        return button;
    }
}
