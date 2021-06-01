/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_panels;

import ReadRules.ReadRules;
import animation.GameState;
import animation.MoneyRain;
import controllers.HighscoreController;
import controllers.LoginController;
import controllers.RulesController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import player.Player;
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
        
        titleLabel = new JLabel("RULES");
        titleLabel.setFont(new Font("", Font.BOLD, 16));
        titleLabel.setSize(400,100);
        titleLabel.setForeground(Color.RED);
        titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(Color.black);
        titlePanel.add(titleLabel);
        
        back = new JButton("Back");
        back.setMaximumSize(new Dimension(200, 75));
        back.setBackground(new Color(213, 53, 42));
        back.setForeground(Color.white);
        back.setFocusable(false);
        
        buttonPanel = new JPanel();
        buttonPanel.add(back);
        
        String rules = ReadRules.getRules();
        rulesTextField = new JTextField();
        //rulesTextField.setText(rules);
        //rulesTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        rulesTextField.setEditable(false);
        rulesTextField.setMaximumSize(new Dimension(500, 500));
        rulesPanel = new JPanel();
        //rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
        //rulesPanel.add(Box.createRigidArea(new Dimension(10,10)), BorderLayout.SOUTH);
        rulesPanel.setLayout(new BorderLayout());
        //rulesPanel.add(rulesTextField, BorderLayout.CENTER);
        //rulesPanel.add(Box.createRigidArea(new Dimension(200, 680)), BorderLayout.SOUTH);
       
       JTextArea jta = new JTextArea("Hi");
       jta.setEditable(false);
       rulesPanel.add(jta, BorderLayout.CENTER);
       Component rigidArea = Box.createRigidArea(new Dimension(50, 100));
       rigidArea.setBackground(Color.BLACK);
       rulesPanel.add(rigidArea, BorderLayout.SOUTH);
        
               
   
        

        super.setLayout(new BorderLayout());
        super.setBackground(backgroundColor);
        super.add(back, BorderLayout.SOUTH);
        super.add(titlePanel, BorderLayout.CENTER);
        super.add(rulesPanel, BorderLayout.CENTER);
        super.add(new moneyPanels(400, 720), BorderLayout.EAST);
        super.add(new moneyPanels(400, 720), BorderLayout.WEST);
        //super.add(Box.createRigidArea(new Dimension(400, 720)), BorderLayout.WEST);

        
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
    



