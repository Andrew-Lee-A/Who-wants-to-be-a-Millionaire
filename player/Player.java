package player;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class Player {
    private String username;
    private int currentHighscore;
    
    public Player(String username) throws IllegalArgumentException, NullPointerException{
        if(username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be an empty string");
        }
        
        this.username = username;
        this.currentHighscore = 0;
    }
    
    public Player() {
        this.username = "";
        this.currentHighscore = 0;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the currentHighscore
     */
    public int getCurrentHighscore() {
        return currentHighscore;
    }

    /**
     * increment the highscore by 1
     */
    public void incrementHighscore() {
        this.currentHighscore++;
    }
    
    public void setHighscore(int num) {
        if(num >= 0 && num <= 15) {
            this.currentHighscore = num;
        }
    }
    
    public void setUsername(String username) {
        if(username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be an empty string");
        }
        
        this.username = username;
    }
}
