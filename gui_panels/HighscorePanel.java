/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_panels;

import animation.GameState;
import controllers.HighscoreController;
import controllers.LoginController;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Andrew Lee 17983766
 */
public class HighscorePanel extends JPanel implements Observer{

    private final JButton back;
    private final GameState gameState;
    private final JTable table;
    
    public HighscorePanel(int width, int height, GameState gameState, Color backgroundColor){
        this.gameState = gameState;

        
        back = new JButton("Back");
        back.setMaximumSize(new Dimension(200, 75));
        back.setBackground(new Color(213, 53, 42));
        back.setForeground(Color.white);
        back.setFocusable(false);
        
        // @Rhys, If you see this part, data variable is a 2d array
        // with first column player names and second volumn highscores
        // I need you to get these values from the database. ty
        String[] columnNames = {"Player", "HighScore"};
        String[][] data = {
            {"Andrew","5"}, {"Kerry","2"}, {"Cheema","3"},
            {"Carrie","14"}, {"Rhys","9"}, {"Steffan","1"}
        };
        
        // Sort the array by the highest score
        Arrays.sort(data, (a,b) ->{
            String t1 = a[1];
            String t2 = b[1];
            int compare = Integer.compare(Integer.parseInt(t1), Integer.parseInt(t2));
            if (compare == 0){
                return 0;
            }else if (compare > 0){
                return -1;
            }else{
                return 1;
            }
        });
        
        JPanel tablePanel = new JPanel();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        //table.setSize(new Dimension(500, 250));
        table.setForeground(Color.red);
        //table.setPreferredScrollableViewportSize(new Dimension(450,63));
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        tablePanel.add(scrollPane);
        tablePanel.setSize(400, 1000);
        
        JPanel jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setSize(200, 500);
        
        JTextField usernameEntry = new JTextField();
        usernameEntry.setMaximumSize(new Dimension(300, 300));
        usernameEntry.setBackground(backgroundColor);
        usernameEntry.setForeground(Color.red);
        usernameEntry.setCaretColor(Color.white);
        usernameEntry.setFont(new Font("", Font.BOLD, 16));
        
        jp.add(usernameEntry);
        
        
        super.setSize(width, height);
        super.setLayout(new BorderLayout());
        super.setBackground(backgroundColor);
        super.add(back, BorderLayout.SOUTH);
        super.add(jp, BorderLayout.WEST);
        super.add(tablePanel, BorderLayout.CENTER);
    }    
    
    public void addHighScoreController(HighscoreController controller){
        back.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        HighscoreController model = (HighscoreController) o;
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

