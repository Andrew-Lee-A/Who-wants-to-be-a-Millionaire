package driver;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import animation.GameState;
import game_db.GameDBManager;
import gui_panels.LoginPanel;
import gui_panels.MainMenuPanel;
import gui_panels.PlayGamePanel;
import java.awt.Color;
import question.QuestionTimer;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class GameDriver {

    private final static int GAME_WIDTH = 1280;
    private final static int GAME_HEIGHT = 720;
    private final static Dimension GAME_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    private final static String LOGIN = "login"; private final static String MAIN_MENU = "main menu";
    private final static String PLAY_GAME = "play game"; private final static String HIGHSCORES = "highscores";
    private final static String RULES = "rules";
    
    public static void main(String[] args) {
        
        CardLayout card = new CardLayout();
        JPanel selectedPanel = new JPanel();
        selectedPanel.setSize(GAME_SIZE);
        selectedPanel.setLayout(card);

        GameState currentGameState = new GameState(selectedPanel, card);
        
        // Create all panels to add to card layout...
        PlayGamePanel playGamePanel;
        playGamePanel = new PlayGamePanel(GAME_WIDTH, GAME_HEIGHT, currentGameState);
        QuestionTimer questionTimer = playGamePanel.getCounterTImer();
        MainMenuPanel mainMenuPanel = new MainMenuPanel(GAME_SIZE, currentGameState, playGamePanel, questionTimer);
        LoginPanel loginPanel = new LoginPanel(GAME_WIDTH, GAME_HEIGHT, currentGameState, Color.black);
        
        // set the panel transitions
        selectedPanel.add(loginPanel, LOGIN);
        selectedPanel.add(mainMenuPanel, MAIN_MENU);
        selectedPanel.add(playGamePanel, PLAY_GAME);
        currentGameState.setLogin(LOGIN);
        currentGameState.setMainMenu(MAIN_MENU);
        currentGameState.setPlayGame(PLAY_GAME);
        
        //start db
        GameDBManager.connectToDB();
        
        JFrame gameFrame = new JFrame("Who Wants To Be A Millionaire");
        gameFrame.setSize(GAME_SIZE);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.getContentPane().add(selectedPanel);
        gameFrame.setVisible(true);
    }
}
