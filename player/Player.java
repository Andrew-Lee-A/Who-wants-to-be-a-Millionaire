package player;

import java.util.Observable;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class Player extends Observable {

    private String username;
    private int currentHighscore;

    public Player(String username) throws IllegalArgumentException, NullPointerException {
        if (username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be an empty string");
        }

        this.username = username;
        this.currentHighscore = 0;
    }
    
    public Player(String username, int highscore) throws IllegalArgumentException, NullPointerException {
        if (username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be an empty string");
        }
        this.setHighscore(highscore);
        this.username = username;
        this.currentHighscore = highscore;
    }

    public Player() {
        this.username = "";
        this.currentHighscore = 0;
    }
    
    public void notifyView() {
        this.setChanged();
        this.notifyObservers(this);
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
     * increment the highscore by 1 if it wont go over the bounds
     */
    public void incrementHighscore() {
        if (currentHighscore + 1 <= 15) {
            this.currentHighscore++;
            super.setChanged();
            super.notifyObservers(this);
        }
    }

    public void setHighscore(int num) {
        if (num >= 0 && num <= 15) {
            this.currentHighscore = num;
            super.setChanged();
            super.notifyObservers(this);
        }
    }

    public void setUsername(String username) {
        if (username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be an empty string");
        }

        this.username = username;
    }
}
