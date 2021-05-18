/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_panels;

import animation.GameState;
import controllers.HighscoreController;
import game_db.GameDBManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.table.DefaultTableModel;
import player.Player;
/**
 *
 * @author Andrew Lee 17983766
 */
public class HighscorePanel extends JPanel implements Observer{

    private final JButton back;
    private final GameState gameState;
    private final JTable table;
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
        
        
        
//        String[][] data = {
//            {"Andrew","5"}, {"Kerry","2"}, {"Cheema","3"},
//            {"Carrie","14"}, {"Rhys","9"}, {"Steffan","1"}
//        };

        //Set up database data for JTable
        String[] columnNames = {"Player", "HighScore"};
        String[][] data;
        playerList = GameDBManager.getPlayerList();
        data = toDataArray(playerList);
       
        //Set up the table panel and the JTable to represent data
        JPanel tablePanel = new JPanel();
        tablePanel.setSize(400, 1000);
        
        //Set up table
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setForeground(Color.red);
        //table.setPreferredScrollableViewportSize(new Dimension(450,63));
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
        super.add(back, BorderLayout.SOUTH);
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
    
    /**
     * takes a string and returns the related button
     * @param btnName
     * @return
     * @throws IllegalArgumentException 
     */
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
    private String[][] toDataArray(ArrayList<Player> playerList){
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
}

