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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import animation.CurrentPanel;
import gui_styling.OnHoverButton;

/**
 * This class is used to create the main menu buttons for the who wants to be a
 * millionaire game
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class MainMenuButtonPanel extends JPanel {

    private final JButton playButton;
    private final JButton highscoreButton;
    private final JButton rulesButton;
    private final JButton exitButton;
    private final int NUM_BUTTONS = 4;
    private final JButton[] allButtons;

    public MainMenuButtonPanel(Dimension size, Dimension buttonSize, Color backgroundColor,
            Color defaultButtonColor, Color onHoverColor, CurrentPanel currentPanel, PlayGamePanel gamePanel, Timer counterTimer) {
        //initialize buttons
        playButton = new JButton("Play");
        highscoreButton = new JButton("Highscores");
        rulesButton = new JButton("Rules");
        exitButton = new JButton("Exit");

        allButtons = new JButton[NUM_BUTTONS];
        allButtons[0] = playButton;
        allButtons[1] = highscoreButton;
        allButtons[2] = rulesButton;
        allButtons[3] = exitButton;

        // when playing a game start the timer
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counterTimer.start();
                currentPanel.goToPlayGame();
            }
        });

        // when pressing exit, end the programme
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        for (int i = 0; i < NUM_BUTTONS; i++) {
            allButtons[i].setMaximumSize(buttonSize);
            allButtons[i].setAlignmentX(MainMenuButtonPanel.CENTER_ALIGNMENT);
            allButtons[i].setBackground(defaultButtonColor);
            allButtons[i].setFocusable(false);
            allButtons[i].addMouseListener(new OnHoverButton(allButtons[i], defaultButtonColor, onHoverColor));
            allButtons[i].addMouseListener(new OnHoverTextColor(allButtons[i]));
        }
        
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

    private class OnHoverTextColor extends MouseAdapter {
        private final JButton btn;
        
        public OnHoverTextColor(JButton b) {
            this.btn = b;
        }
        
        @Override
        public void mouseEntered(MouseEvent event) {
            btn.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent event) {
            btn.setForeground(Color.BLACK);
        }
    }
}
