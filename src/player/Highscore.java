/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.util.InputMismatchException;
import enums.WinningAmount;

/**
 * This class is used to represent a high score within the game, it also
 * stores the amount of winnings that would of been earned given the total
 * questions correct. Note that one cannot set any of the input parameter they are
 * fixed after the object is created.
 * @author Rhys Van Rooyen Student ID: 19049569
 */
public class Highscore {

    private String username;
    private int score;
    private WinningAmount winnings;

    public Highscore(String username, int score) throws InputMismatchException, NullPointerException {
        if(username == null) {
            throw new InputMismatchException("username cannot be null");
        } else if (username.trim().length() == 0) {
            throw new InputMismatchException("username cannot be empty");
        }
        
        if(score < 0 || score > 15) {
            throw new InputMismatchException("score parameter cannot be less than 0 or greater than 15");
        }
        
        this.username = username;
        
        this.score = score;

        switch (score) {
            case 0:
                winnings = WinningAmount.$0;
                break;
            case 1:
                winnings = WinningAmount.$100;
                break;
            case 2:
                winnings = WinningAmount.$200;
                break;
            case 3:
                winnings = WinningAmount.$300;
                break;
            case 4:
                winnings = WinningAmount.$500;
                break;
            case 5:
                winnings = WinningAmount.$1000;
                break;
            case 6:
                winnings = WinningAmount.$2000;
                break;
            case 7:
                winnings = WinningAmount.$4000;
                break;
            case 8:
                winnings = WinningAmount.$8000;
                break;
            case 9:
                winnings = WinningAmount.$16000;
                break;
            case 10:
                winnings = WinningAmount.$32000;
                break;
            case 11:
                winnings = WinningAmount.$64000;
                break;
            case 12:
                winnings = WinningAmount.$125000;
                break;
            case 13:
                winnings = WinningAmount.$250000;
                break;
            case 14:
                winnings = WinningAmount.$500000;
                break;
            case 15:
                winnings = WinningAmount.$1000000;
                break;
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the winnings
     */
    public WinningAmount getWinnings() {
        return winnings;
    }
}
