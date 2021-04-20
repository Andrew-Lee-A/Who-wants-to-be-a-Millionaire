/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_components;

import gui_panels.MainMenuButtonPanel;
import gui_styling.OnHoverButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * This class is used to represent the generic styling of a button within the
 * main menu buttons, and initialize said buttons
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class MainMenuButton {

    public static JButton createMenuButton(String text, Dimension buttonSize, Color defaultButtonColor, Color onHoverColor) {
        JButton menuButton = new JButton(text);
        menuButton.setMaximumSize(buttonSize);
        menuButton.setAlignmentX(MainMenuButtonPanel.CENTER_ALIGNMENT);
        menuButton.setBackground(defaultButtonColor);
        menuButton.setFocusable(false);
        menuButton.addMouseListener(new OnHoverButton(menuButton, defaultButtonColor, onHoverColor));
        menuButton.addMouseListener(new OnHoverTextColor(menuButton));

        return menuButton;
    }

    private static class OnHoverTextColor extends MouseAdapter {

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
