package gui_panels;

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
    public LoginPanel(int width, int height, Color backgroundColor) {
        warning = new JLabel("Warning, if a username that already exists is entered all information related to that username will be overwritten after a game"
                + " has been played!", SwingConstants.CENTER);
        warning.setSize(500, 100);
        warning.setFont(new Font("", Font.PLAIN, 16));
        Color redText = new Color(213, 53, 42);
        warning.setForeground(redText);
        
        BorderLayout bl = new BorderLayout();
        bl.setVgap(250);
        super.setSize(width, height);
        super.setLayout(bl); 
        super.add(warning, BorderLayout.NORTH);
        super.add(new UsernameField(width, height, backgroundColor, redText), BorderLayout.CENTER);
        super.setBackground(backgroundColor);
    }
}
