package game_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import player.Player;

/**
 * singleton class used for managing database
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class GameDBManager {

    private final static String GAME_DB_USERNAME = "pdc";
    private final static String GAME_DB_PASSWORD = "pdc";
    private final static String DB_URL = "jdbc:derby:WhoWantsToBeAMillionaireDB; create=true";
    private static Connection dbConnection;
    private static Statement statement;
    private static GameDBManager dbManager = null;

    private GameDBManager() {
    }
    
    public static GameDBManager getDbManagerInstance() {
        if(dbManager == null) {
            dbManager = new GameDBManager();
        }
        
        return dbManager;
    }

    /**
     * connect to the db
     */
    public static void connectToDB() {
        try {
            dbConnection = DriverManager.getConnection(DB_URL, GAME_DB_PASSWORD, GAME_DB_USERNAME);
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * disconnect the db
     */
    public static void disconnectFromDB() {
        if (dbConnection == null) {
            return;
        }

        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * This method takes a player to look for through the records and returns
     * the player if they were found, returns null if no player was found. Note
     * that the players name is used to determine if they exists (primary_key)
     * in the db where the name is not case sensitive
     *
     * @param p, type Player -> player to search for
     * @return Player, the player found, null if no player found
     */
    public static Player doesPlayerExist(Player p) {

        try {
            statement = dbConnection.createStatement();
            ResultSet rsCursor = statement.executeQuery("SELECT * FROM PLAYER");

            while (rsCursor.next()) {
                String username = rsCursor.getString("USERNAME");
                int highscore = rsCursor.getInt("HIGHSCORE");

                if (p.getUsername().equalsIgnoreCase(username)) {
                    Player foundPlayer = new Player(username);
                    foundPlayer.setHighscore(highscore);
                    return foundPlayer;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * this method is used to insert a player into the records for the db it
     * first checks if the player exits, after which it will remove the player
     * from the records, and re insert them. Otherwise it will just insert the
     * player;
     *
     * @param p type Player, the player to insert
     * @param newPlayer a boolean representing if the player is new
     */
    public static void updateRecords(Player p, boolean newPlayer) {
        try {
            statement = dbConnection.createStatement();

            //Check if the player already exists in db and remove them
            // if they do
            String sqlStatement;
            if (!newPlayer) {
                sqlStatement = "DELETE FROM PLAYER WHERE UPPER(USERNAME) = " + "UPPER('" + p.getUsername() + "')";
                statement.executeUpdate(sqlStatement);
            }

            sqlStatement = "INSERT INTO PLAYER VALUES (" + "'" + p.getUsername() + "'" + ", " + p.getCurrentHighscore() + ")";
            statement.executeUpdate(sqlStatement);

            statement.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * This method should only be used if the tables in the db do not exist it
     * creates the two tables used for the game
     */
    public static void makeTables() {
        boolean[] tableExists = checkTablesExist();

        try {
            statement = dbConnection.createStatement();
            
            String sqlStatement;
            if (!tableExists[0]) {
                // Create player table
                sqlStatement = "CREATE TABLE PLAYER (USERNAME VARCHAR(50), HIGHSCORE INT)";
                statement.executeUpdate(sqlStatement);
            }

            if (!tableExists[1]) {
                // Create highscore table to map different highscores to winnings
                sqlStatement = "CREATE TABLE WINNINGS (SCORE INT, AMOUNT VARCHAR(50))";
                statement.executeUpdate(sqlStatement);

                // Highscores is a static table so initial insertions may be made
                sqlStatement = "INSERT INTO WINNINGS VALUES "
                        + "(0, '$0'), (1, '$100'), (2, '$200'),"
                        + "(3, '$300'), (4, '$500'), (5, '$1,000'),"
                        + "(6, '$2,000'), (7, '$4,000'), (8, '$8,000'),"
                        + "(9, '$16,000'), (10, '$32,000'), (11, '$64,000'),"
                        + "(12, '$125,000'), (13, '$250,000'), (14, '$500,000'),"
                        + "(15, '$1,000,000')";
                statement.executeUpdate(sqlStatement);
            }
            
            statement.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * This method determines if the tables player and winnings
     * currently exist within the database
     * @return a boolean array where the first value represents if the player table is in
     * the db and the second value represents the winnings table being in the db
     */
    public static boolean[] checkTablesExist() {
        // note that the first index represents the player and the second index
        // represents the winnings
        boolean[] tablesExist = new boolean[2];
        Arrays.fill(tablesExist, false);

        try {
            ResultSet rs = dbConnection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                String currentTable = rs.getString("TABLE_NAME");
                if (currentTable.equalsIgnoreCase("player")) {
                    tablesExist[0] = true;
                } else if (currentTable.equalsIgnoreCase("winnings")) {
                    tablesExist[1] = true;
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return tablesExist;
    }
    
    /**
     * method is used to get a list of player scores stored in the db
     * currently
     * @return a list of all the players in the db
     */
    public static ArrayList<Player> getPlayerList() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            Statement sqlState = dbConnection.createStatement();
            String sqlGetPlayers = "SELECT * FROM player";
            ResultSet rs = sqlState.executeQuery(sqlGetPlayers);
            
            while(rs.next()) {
                String username = rs.getString("username");
                int highscore = rs.getInt("highscore");
                
                players.add(new Player(username, highscore));
            }
        } catch (SQLException e) {
            System.err.println("Could not fetch player list....");
        }
        
        return players;
    }
}
