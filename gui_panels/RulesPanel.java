/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_panels;

import animation.GameState;
import animation.MoneyRain;
import controllers.RulesController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
/**
 *
 * @author Andrew Lee 17983766
 */
public class RulesPanel extends JPanel implements Observer{

    private JButton back;
    private GameState gameState;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel rulesPanel;
    private JLabel titleLabel;
    private JTextField rulesTextField;
    
    
    public RulesPanel(int width, int height, GameState gameState, Color backgroundColor){
        this.gameState = gameState;
        
        //Set up the rules label
        titleLabel = new JLabel("RULES");
        titleLabel.setFont(new Font("", Font.BOLD, 16));
        titleLabel.setSize(400,100);
        titleLabel.setForeground(Color.RED);
        titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(Color.black);
        titlePanel.add(titleLabel);
        
        //set up back button and back panel
        back = new JButton("Back");
        back.setMaximumSize(new Dimension(200, 75));
        back.setBackground(new Color(213, 53, 42));
        back.setForeground(Color.white);
        back.setFocusable(false);
        buttonPanel = new JPanel();
        buttonPanel.add(back);
       
        //Set up textfield for rules
        rulesTextField = new JTextField();
        rulesTextField.setEditable(false);
        rulesTextField.setSize(new Dimension(500, 500));
        rulesPanel = new JPanel();
        rulesPanel.setLayout(new BorderLayout());
        
        //Rules text
       JTextArea jta = new JTextArea("   There are 15 questions within a game every question will result in a point \n" +
"    being earned (which is represented by some amount of money). However, if you\n" +
"    get a question wrong you will have to start again however, don't worry you get to\n" +
"    keep your score ;)!!!!! Furthermore, you can choose to walk away at any time and\n" +
"    keep your points, if you wish to leave. Also you have 3 lifelines to help you become\n" +
"    rich! Firstly 50/50 which will lower the amount of possible answers from 4 to 2,\n" +
"    phone a friend, where a friend will try help you get the correct answer, and\n" +
"    lastly ask the audience where the audience will vote on what they think the\n" +
"    answer is. Please note none of these lifelines completely give you the\n" +
"    correct answer they just merely guide you! That's all for the rules, now go\n" +
"    become a millionaire!! Note that only 1 life line may be used per round!!!!");
       
       jta.setEditable(false);
       Component rigidArea = Box.createRigidArea(new Dimension(50, 200));
       rigidArea.setBackground(Color.BLACK);
       rulesPanel.add(rigidArea, BorderLayout.SOUTH);
       rulesPanel.add(jta, BorderLayout.CENTER);
       rulesPanel.setBackground(Color.BLACK);

        super.setLayout(new BorderLayout());
        super.setBackground(backgroundColor);
        super.setSize(width, height);
        super.add(back, BorderLayout.SOUTH);
        super.add(titlePanel, BorderLayout.NORTH);
        super.add(rulesPanel, BorderLayout.CENTER);
        
        //Money thread
        super.add(new moneyPanels(400, 720), BorderLayout.EAST);
        super.add(new moneyPanels(400, 720), BorderLayout.WEST);     
    }    
    
    public void addRulesController(RulesController controller){
        back.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        RulesController model = (RulesController) o;
        if(model.getModelSelection().equalsIgnoreCase("main menu")){
            gameState.goToMainMenu();
        }
    }
    
    public JButton getButton(String btnName) throws IllegalArgumentException{
        if(btnName.equalsIgnoreCase("back")){
            return this.back;
        }
        throw new IllegalArgumentException("Invalid button name");
    }
    
    private class moneyPanels extends JPanel implements ActionListener{
        private final MoneyRain[] moneyRain;
        private final int height, width;
        private final Timer moneyTimer;
        
        public moneyPanels(int width, int height){
            this.height = height;
            this.width = width;
            super.setPreferredSize(new Dimension(width, height));
            super.setBackground(Color.black);
            
            moneyTimer = new Timer(10, this);
            moneyTimer.start();
        
            moneyRain = new MoneyRain[10];
            for (int i = 0; i < 10; i++) {
                moneyRain[i] = new MoneyRain(this.width, this.height, "$");
            }
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
    }
    
}
    



