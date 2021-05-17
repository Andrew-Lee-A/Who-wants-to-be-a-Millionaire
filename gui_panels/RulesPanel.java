/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_panels;

import ReadRules.ReadRules;
import animation.GameState;
import controllers.HighscoreController;
import controllers.LoginController;
import controllers.RulesController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
        rulesTextField.setText("\n\n\n\n\n\n\nhello");
        rulesTextField.setEditable(false);
        rulesTextField.setMaximumSize(new Dimension(900, 500));
        rulesPanel = new JPanel();
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
        rulesPanel.add(Box.createRigidArea(new Dimension(0,5)));
       // rulesPanel.add(rulesTextField, BorderLayout.CENTER);
       
       JTextArea jta = new JTextArea(rules);
       rulesPanel.add(jta, BoxLayout.Y_AXIS);
        
        //rulesPanel.setSize(1280-400, 500);
        

        super.setLayout(new BorderLayout());
        super.setBackground(backgroundColor);
        super.add(buttonPanel, BorderLayout.SOUTH);
        super.add(titlePanel, BorderLayout.PAGE_START);
        super.add(rulesPanel, BorderLayout.CENTER);

        
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
}

