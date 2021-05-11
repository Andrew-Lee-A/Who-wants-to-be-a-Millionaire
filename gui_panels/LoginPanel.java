package gui_panels;

import animation.GameState;
import controllers.LoginController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import player.Player;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class LoginPanel extends JPanel implements Observer {

    private final JLabel warning;
    private final JTextField usernameEntry;
    private final JLabel usernameLabel;
    private final JButton submit;
    private final GameState gameState;

    public LoginPanel(int width, int height, GameState gameState, Color backgroundColor) {
        // create warning label
        warning = new JLabel("Warning, if a username that already exists is entered the "
                + "high score will be overwriten if you achieve a higher score!", SwingConstants.CENTER);
        warning.setSize(500, 100);
        warning.setFont(new Font("", Font.PLAIN, 16));
        Color redText = new Color(213, 53, 42);
        warning.setForeground(redText);
        warning.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set username text field for entry
        usernameEntry = new JTextField();
        usernameEntry.setMaximumSize(new Dimension(300, 25));
        usernameEntry.setBackground(backgroundColor);
        usernameEntry.setForeground(redText);
        usernameEntry.setCaretColor(Color.white);
        usernameEntry.setFont(new Font("", Font.BOLD, 16));
        usernameEntry.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set JLabel for username input
        usernameLabel = new JLabel("Please enter username here (20 characters max):");
        usernameLabel.setSize(400, 100);
        usernameLabel.setForeground(redText);
        usernameLabel.setFont(new Font("", Font.BOLD, 16));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // create a submission button
        submit = new JButton("Submit");
        submit.setMaximumSize(new Dimension(200, 75));
        submit.setBackground(redText);
        submit.setForeground(Color.white);
        submit.setFocusable(false);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);

        // set the currentPanel used for changing panels
        this.gameState = gameState;

        // create the username entry field
        //enterUsername = new UsernameField(width, height, gameState, backgroundColor, redText);
        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);

        super.setSize(width, height);
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.add(warning);
        super.add(Box.createRigidArea(new Dimension(0, 200)));
        super.add(usernameLabel);
        super.add(Box.createRigidArea(new Dimension(0, 10)));
        super.add(usernameEntry);
        super.add(Box.createRigidArea(new Dimension(0, 10)));
        super.add(submit);
        super.setBackground(backgroundColor);
    }

    /**
     * Used to fetch the JTextField current string
     *
     * @return the string stored in the JTextField
     */
    public String getUsername() {
        return usernameEntry.getText();
    }

    public void addLoginController(LoginController controller) {
        submit.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        gameState.setPlayer((Player) o);
        gameState.goToMainMenu();
        o.deleteObserver(this); // can never return to the login panel...
    }
}
