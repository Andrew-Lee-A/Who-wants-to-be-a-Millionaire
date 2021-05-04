package animation;

import java.awt.CardLayout;
import javax.swing.JPanel;
import player.Player;

/**
 * This class is used to model the current state of the game, i.e.
 * the player and their current score, the current panel or screen
 * the game is at... etc
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class GameState {
    private final JPanel selectedPanel;
    private final CardLayout cardLayout;
    private String login; private String mainMenu; private String playGame;
    private String highscores; private String rules;
    private Player p;
    
    public GameState(JPanel selectedPanel, CardLayout cardLayout) {
        this.selectedPanel = selectedPanel;
        this.cardLayout = cardLayout;
        
        // set all the panel identifies to blank at first
        this.login = "";
        this.mainMenu = "";
        this.playGame = "";
        this.highscores = "";
        this.rules = "";
        
        // set player to null on creation
        this.p = null;
    }
    
    public void goToMainMenu() {
        cardLayout.show(selectedPanel, getMainMenu());
    }
    
    public void goToPlayGame() {
        cardLayout.show(selectedPanel, getPlayGame());
    }
    
    public void goToLogin() {
        cardLayout.show(selectedPanel, getLogin());
    }
    
    public void goToHighscores() {
        cardLayout.show(selectedPanel, getHighscores());
    }
    
    public void goToRules() {
        cardLayout.show(selectedPanel, getRules());
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the mainMenu
     */
    public String getMainMenu() {
        return mainMenu;
    }

    /**
     * @param mainMenu the mainMenu to set
     */
    public void setMainMenu(String mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * @return the playGame
     */
    public String getPlayGame() {
        return playGame;
    }

    /**
     * @param playGame the playGame to set
     */
    public void setPlayGame(String playGame) {
        this.playGame = playGame;
    }

    /**
     * @return the highscores
     */
    public String getHighscores() {
        return highscores;
    }

    /**
     * @param highscores the highscores to set
     */
    public void setHighscores(String highscores) {
        this.highscores = highscores;
    }

    /**
     * @return the rules
     */
    public String getRules() {
        return rules;
    }

    /**
     * @param rules the rules to set
     */
    public void setRules(String rules) {
        this.rules = rules;
    }

    /**
     * @return the p
     */
    public Player getPlayer() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setPlayer(Player p) {
        this.p = p;
    }
}
