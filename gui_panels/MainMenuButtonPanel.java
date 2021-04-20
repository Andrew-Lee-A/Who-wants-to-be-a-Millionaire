/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import animation.CurrentPanel;
import gui_components.MainMenuButton;
import question.QuestionTimer;

/**
 * This class is used to create the main menu buttons for the who wants to be a
 * millionaire game
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class MainMenuButtonPanel extends JPanel {
    private final int NUM_BUTTONS = 4;
    private final JButton[] allButtons;

    public MainMenuButtonPanel(Dimension size, Dimension buttonSize, Color backgroundColor,
            Color defaultButtonColor, Color onHoverColor, CurrentPanel currentPanel, PlayGamePanel gamePanel, QuestionTimer questionTimer) {
        
        allButtons = new JButton[NUM_BUTTONS];
        allButtons[0] = MainMenuButton.createMenuButton("Play", buttonSize, defaultButtonColor, onHoverColor);
        allButtons[1] = MainMenuButton.createMenuButton("Highscores", buttonSize, defaultButtonColor, onHoverColor);;
        allButtons[2] = MainMenuButton.createMenuButton("Rules", buttonSize, defaultButtonColor, onHoverColor);;
        allButtons[3] = MainMenuButton.createMenuButton("Exit", buttonSize, defaultButtonColor, onHoverColor);;

        // when playing a game start the timer
        allButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                questionTimer.startTimer();
                currentPanel.goToPlayGame();
            }
        });

        // when pressing exit, end the programme
        allButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Setting stlying of panel
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setSize(size);

        // adding components with separator
        int separator = (size.height - (4 * buttonSize.height)) / 5;

        for (int i = 0; i < NUM_BUTTONS; i++) {
            super.add(Box.createRigidArea(new Dimension(0, separator)));
            super.add(allButtons[i]);
        }
        // add additional rigid area
        super.add(Box.createRigidArea(new Dimension(0, separator)));
        //super.setBackground(backgroundColor);
        super.setOpaque(false);
    }
}
