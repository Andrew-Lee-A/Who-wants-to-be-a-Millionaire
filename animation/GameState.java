package animation;

import game_db.GameDBManager;
import java.awt.CardLayout;
import javax.swing.JPanel;
import player.Player;

/**
 * This class is used to model the current state of the game, i.e. the player
 * and their current score, the current panel or screen the game is at... etc
 * this class is a singleton class
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class GameState {
    private static GameState gs = null;
    private JPanel selectedPanel;
    private CardLayout cardLayout;
    private boolean lifeLineUsedThisRound;
    private String login;
    private String mainMenu;
    private String playGame;
    private String highscores;
    private String rules;
    private Player p;

    private GameState(JPanel selectedPanel, CardLayout cardLayout) {
        lifeLineUsedThisRound = false;
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
    
    /**
     * note this class will not change the selectedPanel, and card layout manager
     * if it is already been created once... thus null can be parsed if the game state
     * has already been created.
     * @param selectedPanel the selected panels
     * @param cardLayout the card layout
     * @return 
     */
    public static GameState getGameState(JPanel selectedPanel, CardLayout cardLayout) {
        if(gs == null) {
            gs = new GameState(selectedPanel, cardLayout);
        }
        
        return gs;
    }

    /**
     * This method is used to update the records in the db depending on if a
     * player exists or not within the db.
     */
    public void updateRecords() {
        Player doesExist = GameDBManager.doesPlayerExist(p);

        if (doesExist == null) {
            GameDBManager.updateRecords(p, true);
        } else {
            if (p.getCurrentHighscore() > doesExist.getCurrentHighscore()) {
                GameDBManager.updateRecords(p, false);
            }
        }
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

    /**
     * @return the lifeLineUsedThisRound
     */
    public boolean isLifeLineUsedThisRound() {
        return lifeLineUsedThisRound;
    }

    /**
     * @param lifeLineUsedThisRound the lifeLineUsedThisRound to set
     */
    public void setLifeLineUsedThisRound(boolean lifeLineUsedThisRound) {
        this.lifeLineUsedThisRound = lifeLineUsedThisRound;
    }
}
