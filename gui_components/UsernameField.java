package gui_components;

import animation.GameState;
import game_db.GameDBManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import player.Player;

/**
 * This class is used to create a field for the username to be entered
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class UsernameField extends JPanel {

    private final JTextField usernameEntry;
    private final JLabel usernameLabel;
    private final JButton submit;
    private final GameState currentPanel;

    public UsernameField(int width, int height, GameState currentPanel, Color backgroundColor, Color textColor) {
        this.currentPanel = currentPanel;

        // Set username text field for entry
        usernameEntry = new JTextField();
        usernameEntry.setPreferredSize(new Dimension(300, 25));
        usernameEntry.setBackground(backgroundColor);
        usernameEntry.setForeground(textColor);
        usernameEntry.setCaretColor(Color.white);
        usernameEntry.setFont(new Font("", Font.BOLD, 16));

        // Set JLabel for username input
        usernameLabel = new JLabel("Please enter username here (20 characters max):");
        usernameLabel.setSize(400, 100);
        usernameLabel.setForeground(textColor);
        usernameLabel.setFont(new Font("", Font.BOLD, 16));

        // create a submission button
        submit = new JButton("Submit");
        submit.setMaximumSize(new Dimension(200, 100));
        submit.addActionListener(new UsernameSubmission(this));
        submit.setBackground(textColor);
        submit.setForeground(Color.white);
        submit.setFocusable(false);

        super.setSize(width, height);
        super.add(usernameLabel);
        super.add(usernameEntry);
        super.add(submit);
        super.setOpaque(false);
    }

    /**
     * This private class is used to navigate when a username is to be submitted
     * it will respond with appropriate error messages.
     */
    private class UsernameSubmission implements ActionListener {

        private final JPanel component;

        UsernameSubmission(JPanel component) {
            this.component = component;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String usernameEntered = usernameEntry.getText();

            // Make sure a username doesnt have any starting, or trailing whitespace, or is to big
            if (usernameEntered.trim().equals("")) {
                JOptionPane.showMessageDialog(component, "Username cannot be empty!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (usernameEntered.charAt(usernameEntered.length() - 1) == ' ') {
                JOptionPane.showMessageDialog(component, "Username cannot have trailing white-space!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (usernameEntered.length() > 20) {
                JOptionPane.showMessageDialog(component, "Username must not be greater than 20 characters!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (usernameEntered.charAt(0) == ' ') {
                JOptionPane.showMessageDialog(component, "Username must not have leading white-space!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                // Intitialise the player object
                Player p = GameDBManager.doesPlayerExist(new Player(usernameEntered));
                Integer overwriteData = null;
                if (p == null) { // new player
                    p = new Player(usernameEntered);
                    
                    currentPanel.setPlayer(p);
                    currentPanel.goToMainMenu();
                    GameDBManager.updateRecords(p, true);
                } else { // returning player
                    overwriteData = JOptionPane.showOptionDialog(component, "Username found... WARNING your highscore will be overwriten if you achieve a higher score, continue?",
                            "WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Continue", "Cancel"}, JOptionPane.YES_OPTION);
    
                    if (overwriteData == JOptionPane.YES_OPTION) {
                        //change the state of the game
                        p.setHighscore(0);
                        currentPanel.setPlayer(p);
                        currentPanel.goToMainMenu();
                    }
                }
            }
        }
    }

}
