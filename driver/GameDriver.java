/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import animation.CurrentPanel;
import game_db.GameDBManager;
import gui_panels.MainMenuPanel;
import gui_panels.PlayGamePanel;
import player.Player;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class GameDriver {
    private final static int GAME_WIDTH = 1280;
    private final static int GAME_HEIGHT = 720;
    private final static Dimension GAME_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    public static void main(String[] args) {
        Player p = new Player("Teng");
        
        CardLayout card = new CardLayout();
        JPanel selectedPanel = new JPanel();
        selectedPanel.setSize(GAME_SIZE);   
        selectedPanel.setLayout(card);
        
        CurrentPanel currentPanel = new CurrentPanel(selectedPanel, card);
        
        PlayGamePanel playGamePanel;
        playGamePanel = new PlayGamePanel(GAME_WIDTH, GAME_HEIGHT, currentPanel);
        Timer counterTimer = playGamePanel.getCounterTImer();
        MainMenuPanel mainMenuPanel = new MainMenuPanel(GAME_SIZE, currentPanel, playGamePanel, counterTimer, p.getUsername());
        
        selectedPanel.add(mainMenuPanel, "main menu");
        selectedPanel.add(playGamePanel, "play game");
        
        JFrame gameFrame = new JFrame("Who Wants To Be A Millionaire");
        gameFrame.setSize(GAME_SIZE);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.getContentPane().add(selectedPanel);
        gameFrame.setVisible(true);
    }
}
