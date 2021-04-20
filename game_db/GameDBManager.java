package game_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import player.Player;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class GameDBManager {

    private final static String GAME_DB_USERNAME = "pdc";
    private final static String GAME_DB_PASSWORD = "pdc";
    private final static String DB_URL = "jdbc:derby:WhoWantsToBeAMillionaireDB; create=true";
    private static Connection dbConnection;

    public GameDBManager() {
    }

    public static void connectToDB() {
        try {
            dbConnection = DriverManager.getConnection(DB_URL, GAME_DB_PASSWORD, GAME_DB_USERNAME);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

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

    public static Player fetchPlayer() {
        return null;
    }

    public static void updateRecords(Player p) {

    }

    /**
     * This method should only be used if the tables in the db do not exist
     */
    public static void makeTables() {
        try {
            Statement statement = dbConnection.createStatement();
            
            // Create player table
            String sqlStatement = "CREATE TABLE PLAYER (USERNAME VARCHAR(50), HIGHSCORE INT)";
            statement.executeUpdate(sqlStatement);
            
            // Create highscore table to map different highscores to winnings
            sqlStatement = "CREATE TABLE WINNINGS (SCORE INT, AMOUNT VARCHAR(50))";
            statement.executeUpdate(sqlStatement);
            
            statement.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
