package player;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class Player {
    private String username;
    private int currentHighscore;
    private boolean isReturning; //The player is a returning player i.e. they exist in the db
    
    public Player(String username) throws IllegalArgumentException, NullPointerException{
        if(username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be an empty string");
        }
        
        this.username = username;
        this.currentHighscore = 0;
        this.isReturning = false;
    }
    
    public Player() {
        this.username = "";
        this.currentHighscore = 0;
        this.isReturning = false;
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

    /**
     * @return the isReturning
     */
    public boolean isReturning() {
        return isReturning;
    }

    /**
     * @param isReturning the isReturning to set
     */
    public void setIsReturning(boolean isReturning) {
        this.isReturning = isReturning;
    }
}
