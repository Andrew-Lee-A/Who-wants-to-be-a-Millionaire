package gui_panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import animation.CurrentPanel;
import animation.MoneyRain;
import question.QuestionTimer;

/**
 * This class is used to display the GUI for the main menu of the game, allowing
 * users to traverse to next and prior screens.
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class MainMenuPanel extends JPanel implements ActionListener {

    JPanel mainMenuButtonPanel;
    private final Dimension MENU_BUTTON_SIZE = new Dimension(200, 65);
    private final JLabel welcomeLabel;
    private final Timer moneyTimer;
    private QuestionTimer questionTimer;
    private final MoneyRain[] moneyRain; private final int NUM_DOLLARS = 100;
    private final Color BACKGROUND_COLOR = new Color(0, 0, 0);
    private final CurrentPanel currentPanel;
    
    public MainMenuPanel(Dimension size, CurrentPanel currentPanel, PlayGamePanel gamePanel, QuestionTimer questionTimer, String username) {
        this.currentPanel = currentPanel;
        this.questionTimer = questionTimer;
        // getting button panel
        mainMenuButtonPanel = new MainMenuButtonPanel(new Dimension((MENU_BUTTON_SIZE.width), size.height), MENU_BUTTON_SIZE, new Color(52, 12, 132), 
                new Color(255, 255, 255), new Color(64, 64, 206), currentPanel, gamePanel, questionTimer);
        
        // set label
        welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setFont(new Font("", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(64, 64, 206));

        // set up background balls
        moneyTimer = new Timer(10, this);
        moneyTimer.start();
        
        moneyRain = new MoneyRain[NUM_DOLLARS];
        for(int i = 0; i < NUM_DOLLARS; i++) {
            moneyRain[i] = new MoneyRain(size.width, size.height, "$");
        }

        // styling this panel
        super.setLayout(new BorderLayout());
        super.setSize(size);
        super.add(mainMenuButtonPanel, BorderLayout.CENTER);
        super.add(welcomeLabel, BorderLayout.BEFORE_FIRST_LINE);
        super.setBackground(BACKGROUND_COLOR);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(MoneyRain m : moneyRain) {
            m.drawMoney(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(MoneyRain m: moneyRain) {
            m.moveMoney();
        }
        super.repaint();
    }

}
