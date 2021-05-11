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
import animation.GameState;
import animation.MoneyRain;
import controllers.MenuNavController;
import gui_components.MainMenuButton;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import question.QuestionTimer;

/**
 * This class is used to display the GUI for the main menu of the game, allowing
 * users to traverse to next and prior screens.
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class MainMenuPanel extends JPanel implements Observer, ActionListener {

//    JPanel mainMenuButtonPanel;
    private final Dimension MENU_BUTTON_SIZE = new Dimension(200, 65);
    private final Color btnColor = new Color(255, 255, 255);
    private final Color onHoverBtnColor = new Color(52, 12, 132);
    private final JLabel welcomeLabel;
    private final Timer moneyTimer;
    private QuestionTimer questionTimer;
    private final MoneyRain[] moneyRain;
    private final int NUM_DOLLARS = 100;
    private final Color BACKGROUND_COLOR = new Color(0, 0, 0);
    private final GameState gameState;
    private final int NUM_BUTTONS = 4;
    private final JButton[] allButtons;

    public MainMenuPanel(Dimension size, GameState gameState, PlayGamePanel gamePanel, QuestionTimer questionTimer) {
        this.gameState = gameState;
        this.questionTimer = questionTimer;
        // getting button panel
//        mainMenuButtonPanel = new MainMenuButtonPanel(new Dimension((MENU_BUTTON_SIZE.width), size.height), MENU_BUTTON_SIZE, new Color(52, 12, 132),
//                new Color(255, 255, 255), new Color(64, 64, 206), gameState, gamePanel, questionTimer);

        allButtons = new JButton[NUM_BUTTONS];
        allButtons[0] = MainMenuButton.createMenuButton("Play", MENU_BUTTON_SIZE, btnColor, onHoverBtnColor);
        allButtons[1] = MainMenuButton.createMenuButton("Highscores", MENU_BUTTON_SIZE, btnColor, onHoverBtnColor);
        allButtons[2] = MainMenuButton.createMenuButton("Rules", MENU_BUTTON_SIZE, btnColor, onHoverBtnColor);
        allButtons[3] = MainMenuButton.createMenuButton("Exit", MENU_BUTTON_SIZE, btnColor, onHoverBtnColor);

        // set label
        welcomeLabel = new JLabel("Welcome to Who Wants To Be A Millionaire!");
        welcomeLabel.setFont(new Font("", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(64, 64, 206));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        // set up background balls
        moneyTimer = new Timer(10, this);
        moneyTimer.start();

        moneyRain = new MoneyRain[NUM_DOLLARS];
        for (int i = 0; i < NUM_DOLLARS; i++) {
            moneyRain[i] = new MoneyRain(size.width, size.height, "$");
        }

        // styling this panel
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setSize(size);
        super.add(welcomeLabel);

        // establish spacing for buttons
        int separator = 70;
        for (int i = 0; i < NUM_BUTTONS; i++) {
            super.add(Box.createRigidArea(new Dimension(0, separator)));
            allButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            super.add(allButtons[i]);
        }

        super.setBackground(BACKGROUND_COLOR);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (MoneyRain m : moneyRain) {
            m.drawMoney(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (MoneyRain m : moneyRain) {
            m.moveMoney();
        }
        super.repaint();
    }
    
     /**
     * takes a string and returns the related button
     * @param btnName, a string either play, highscores, rules or exit
     * @return JButton representing the string input 
     * @throws IllegalArgumentException 
     */
    public JButton getButton(String btnName) throws IllegalArgumentException {
        if(btnName.equalsIgnoreCase("play")) {
            return allButtons[0];
        } else if (btnName.equalsIgnoreCase("highscores")) {
            return allButtons[1];
        } else if (btnName.equalsIgnoreCase("rules")) {
            return allButtons[2];
        } else if (btnName.equalsIgnoreCase("exit")) {
            return allButtons[3];
        }
        
        throw new IllegalArgumentException("Invalid button name, must be play, highscores, rules, or exit...");
    }
    
    public QuestionTimer getTimer() {
        return this.questionTimer;
    }

    public void addController(ActionListener controller) {
        for(JButton btn : allButtons) {
            btn.addActionListener(controller);
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        MenuNavController model = (MenuNavController)o;
        if(model.getModelSelection().equalsIgnoreCase("play")) {
            gameState.goToPlayGame();
        } else if(model.getModelSelection().equalsIgnoreCase("highscores")) {
            gameState.goToHighscores();
        } else if(model.getModelSelection().equalsIgnoreCase("rules")) {
            gameState.goToRules();
        }
    }

}
