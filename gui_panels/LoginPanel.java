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
    private final GameState gameState;
    
    public LoginPanel(int width, int height, GameState gameState, Color backgroundColor) {
        // create warning label
        warning = new JLabel("Warning, if a username that already exists is entered the "
                + "high score will be overwriten if you achieve a higher score!", SwingConstants.CENTER);
        warning.setSize(500, 100);
        warning.setFont(new Font("", Font.PLAIN, 16));
        Color redText = new Color(213, 53, 42);
        warning.setForeground(redText);
        
        // set the currentPanel used for changing panels
        this.gameState = gameState;
        
        // create the username entry field
        enterUsername = new UsernameField(width, height, gameState, backgroundColor, redText);
        
        BorderLayout bl = new BorderLayout();
        bl.setVgap(250);
        super.setSize(width, height);
        super.setLayout(bl); 
        super.add(warning, BorderLayout.NORTH);
        super.add(enterUsername, BorderLayout.CENTER);
        super.setBackground(backgroundColor);
    }
}
