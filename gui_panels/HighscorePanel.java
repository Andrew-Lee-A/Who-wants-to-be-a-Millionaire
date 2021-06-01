/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_panels;

import animation.GameState;
import animation.MoneyRain;
import controllers.HighscoreController;
import game_db.GameDBManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import player.Player;
/**
 *
 * @author Andrew Lee 17983766
 */
public class HighscorePanel extends JPanel implements Observer{
    public static String[][]data;
    public static DefaultTableModel model;
    private final JButton back;
    private final GameState gameState;
    private static JTable table;
    private ArrayList<Player> playerList;
    private final JLabel highscoreTitleLabel;
    private final JPanel highscoreTitlePanel;
    
    public HighscorePanel(int width, int height, GameState gameState, Color backgroundColor){
        this.gameState = gameState;
        
        //set up title label
        highscoreTitleLabel = new JLabel("HIGHSCORES");
        highscoreTitleLabel.setFont(new Font("", Font.BOLD, 16));
        highscoreTitleLabel.setSize(400,100);
        highscoreTitleLabel.setForeground(Color.RED);
        highscoreTitlePanel = new JPanel();
        highscoreTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        highscoreTitlePanel.setBackground(Color.black);
        highscoreTitlePanel.add(highscoreTitleLabel);
        
        //set up back button
        back = new JButton("Back");
        back.setMaximumSize(new Dimension(200, 75));
        back.setBackground(new Color(213, 53, 42));
        back.setForeground(Color.white);
        back.setFocusable(false);
        
        //Set up database data for JTable
        String[] columnNames = {"Player", "HighScore"};
        //data;
        playerList = GameDBManager.getPlayerList();
        data = toDataArray(playerList);
       
        //Set up the table panel and the JTable to represent data
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.black);
        tablePanel.setSize(600, 1000);
        
        //Set up table
        model = new DefaultTableModel(toDataArray(GameDBManager.getPlayerList()), columnNames){
            @Override
            public boolean isCellEditable(int row, int coloumn){
                // all cells are false;
                return false;
            }
        };
        table = new JTable(model);
        table.setForeground(Color.red);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        
        
        //Set up the table scroll bar
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);
        tablePanel.add(scrollPane);
        
        super.setSize(width, height);
        super.setLayout(new BorderLayout());
        super.setBackground(backgroundColor);
        super.add(highscoreTitlePanel, BorderLayout.NORTH);
        super.add(back, BorderLayout.SOUTH);
        super.add(tablePanel, BorderLayout.CENTER);
        super.add(new moneyPanels(400, 720), BorderLayout.EAST);
        super.add(new moneyPanels(400, 720), BorderLayout.WEST);
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
    
    
    /**
     * Transfer an arrayList of players into a 2d array with the player
     * name and highscore. Sort the array by the greatest scores 
     * @param playerList
     * @return 2d array of playername and highscore
     */
    private static String[][] toDataArray(ArrayList<Player> playerList){
        // Transfer the arraylist of players into a 2d array named 
        // data. Fill with player names and corresponding highscore
        String[][] data = new String[playerList.size()][2];
        for (int i = 0; i < playerList.size(); i++){
            data[i][0] = playerList.get(i).getUsername();
            data[i][1] = Integer.toString(playerList.get(i).getCurrentHighscore());
        }
        //Sort the array by the highest score
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
        
        return data;
    }
    
    /**
     * Static method to retrieve new highscores and update the model
     */
    public static void updateData(){
        String[] columnNames = {"Player", "HighScore"};        
        model = new DefaultTableModel(toDataArray(GameDBManager.getPlayerList()), columnNames){
            @Override
            public boolean isCellEditable(int row, int coloumn){
                // all cells are false;
                return false;
            }
        };
        table.setModel(model);
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

