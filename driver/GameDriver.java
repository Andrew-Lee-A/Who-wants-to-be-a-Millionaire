package driver;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import animation.GameState;
import controllers.HighscoreController;
import controllers.LoginController;
import controllers.MenuNavController;
import controllers.RulesController;
import game_db.GameDBManager;
import gui_panels.HighscorePanel;
import gui_panels.LoginPanel;
import gui_panels.MainMenuPanel;
import gui_panels.PlayGamePanel;
import gui_panels.RulesPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import player.Player;
import question.QuestionTimer;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class GameDriver {

    private final static int GAME_WIDTH = 1280;
    private final static int GAME_HEIGHT = 720;
    private final static Dimension GAME_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    private final static String LOGIN = "login";
    private final static String MAIN_MENU = "main menu";
    private final static String PLAY_GAME = "play game";
    private final static String HIGHSCORES = "highscores";
    private final static String RULES = "rules";

    public static void main(String[] args) {
        // Model for all sub mvc classes
        Player playerModel = new Player();

        CardLayout card = new CardLayout();
        JPanel selectedPanel = new JPanel();
        selectedPanel.setSize(GAME_SIZE);
        selectedPanel.setLayout(card);

        GameState currentGameState = new GameState(selectedPanel, card);

        // Create all panels to add to card layout...
        PlayGamePanel playGamePanel;
        playGamePanel = new PlayGamePanel(GAME_WIDTH, GAME_HEIGHT, currentGameState);

        // Setting up MVC for login functionality
        LoginPanel loginView = new LoginPanel(GAME_WIDTH, GAME_HEIGHT, currentGameState, Color.black);
        LoginController loginController = new LoginController(playerModel, loginView);
        playerModel.addObserver(loginView);
        loginView.addLoginController(loginController);
        
        //Setting up MVC for viewing highscores
        HighscorePanel highScoreView = new HighscorePanel(GAME_WIDTH, GAME_HEIGHT, currentGameState, Color.black);
        HighscoreController highScoreController = new HighscoreController(highScoreView);
        highScoreController.addObserver(highScoreView);
        highScoreView.addHighScoreController(highScoreController);
        
        //Setting up MVC for viewing rules
        RulesPanel rulesView = new RulesPanel(GAME_WIDTH, GAME_HEIGHT, currentGameState, Color.black);
        RulesController rulesController = new RulesController(rulesView);
        rulesController.addObserver(rulesView);
        rulesView.addRulesController(rulesController);
        
        // Setting up MVC for main menu functionality, note that the controller acts
        // as the model and view, as model is a primitive (see controller class for more info).
        QuestionTimer questionTimer = playGamePanel.getCounterTImer();
        MainMenuPanel mainMenuView = new MainMenuPanel(GAME_SIZE, currentGameState, playGamePanel, questionTimer);
        MenuNavController mainMenuController = new MenuNavController(mainMenuView);
        mainMenuController.addObserver(mainMenuView);
        mainMenuView.addController(mainMenuController);
        

        // set the panel transitions
        selectedPanel.add(loginView, LOGIN);
        selectedPanel.add(mainMenuView, MAIN_MENU);
        selectedPanel.add(playGamePanel, PLAY_GAME);
        selectedPanel.add(highScoreView, HIGHSCORES); //TODO make the panels!
        selectedPanel.add(rulesView, RULES);
        currentGameState.setLogin(LOGIN);
        currentGameState.setMainMenu(MAIN_MENU);
        currentGameState.setPlayGame(PLAY_GAME);
        currentGameState.setHighscores(HIGHSCORES);
        currentGameState.setRules(RULES);
        

        //start db
        GameDBManager.connectToDB();

        //create db tables if not present
        GameDBManager.makeTables();

        JFrame gameFrame = new JFrame("Who Wants To Be A Millionaire");
        gameFrame.setSize(GAME_SIZE);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.getContentPane().add(selectedPanel);
        gameFrame.setVisible(true);
    }
}
