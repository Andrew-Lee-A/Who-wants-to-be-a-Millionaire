package gui_panels;

import animation.GameState;
import gui_components.UsernameField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class LoginPanel extends JPanel {
    private final JLabel warning;
    private final UsernameField enterUsername;
    private final GameState currentPanel;
    
    public LoginPanel(int width, int height, GameState currentPanel, Color backgroundColor) {
        // create warning label
        warning = new JLabel("Warning, if a username that already exists is entered all information related to that username will be overwritten after a game"
                + " has been played!", SwingConstants.CENTER);
        warning.setSize(500, 100);
        warning.setFont(new Font("", Font.PLAIN, 16));
        Color redText = new Color(213, 53, 42);
        warning.setForeground(redText);
        
        // set the currentPanel used for changing panels
        this.currentPanel = currentPanel;
        
        // create the username entry field
        enterUsername = new UsernameField(width, height, currentPanel, backgroundColor, redText);
        
        BorderLayout bl = new BorderLayout();
        bl.setVgap(250);
        super.setSize(width, height);
        super.setLayout(bl); 
        super.add(warning, BorderLayout.NORTH);
        super.add(enterUsername, BorderLayout.CENTER);
        super.setBackground(backgroundColor);
    }
}
