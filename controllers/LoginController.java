package controllers;

import game_db.GameDBManager;
import gui_panels.LoginPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import player.Player;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class LoginController implements ActionListener {

    private final LoginPanel loginView;
    private final Player playerModel;

    /**
     * 
     * @param playerModel the player to model
     * @param loginView the respective view / output
     */
    public LoginController(Player playerModel, LoginPanel loginView) {
        this.playerModel = playerModel;
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usernameEntered = loginView.getUsername();

        // Make sure a username doesnt have any starting, or trailing whitespace, or is to big
        if (usernameEntered.trim().equals("")) {
            JOptionPane.showMessageDialog(loginView, "Username cannot be empty!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (usernameEntered.charAt(usernameEntered.length() - 1) == ' ') {
            JOptionPane.showMessageDialog(loginView, "Username cannot have trailing white-space!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (usernameEntered.length() > 20) {
            JOptionPane.showMessageDialog(loginView, "Username must not be greater than 20 characters!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (usernameEntered.charAt(0) == ' ') {
            JOptionPane.showMessageDialog(loginView, "Username must not have leading white-space!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            
            playerModel.setUsername(usernameEntered);
            Player foundPlayer = GameDBManager.doesPlayerExist(playerModel);
            
            Integer overwriteData;
            if (foundPlayer == null) { // new player
                playerModel.notifyView();
                GameDBManager.updateRecords(playerModel, true);
            } else { // returning player
                overwriteData = JOptionPane.showOptionDialog(loginView, "Username found... WARNING your highscore will be overwriten if you achieve a higher score, continue?",
                        "WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Continue", "Cancel"}, JOptionPane.YES_OPTION);

                if (overwriteData == JOptionPane.YES_OPTION) {
                    playerModel.notifyView();
                }
            }
        }
    }
}
